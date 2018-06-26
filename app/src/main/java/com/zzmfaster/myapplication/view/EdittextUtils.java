package com.zzmfaster.myapplication.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EdittextUtils {
    /** edittext只能输入数值的时候做最大最小的限制 */
    public static void setRegion(final EditText edit, final double MIN_MARK, final double MAX_MARK) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double num = Double.parseDouble(s.toString());
                        if (num > MAX_MARK) {
                            s = String.valueOf(MAX_MARK);
                            edit.setText(s);
                        } else if (num < MIN_MARK) {
                            s = String.valueOf(MIN_MARK);
                            edit.setText(s);
                        }
                        edit.setSelection(s.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals("")) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double markVal = 0;
                        try {
                            markVal = Double.parseDouble(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK) {
                            edit.setText(String.valueOf(MAX_MARK));
                            edit.setSelection(String.valueOf(MAX_MARK).length());
                        }
                        return;
                    }
                }
            }
        });
    }
}
