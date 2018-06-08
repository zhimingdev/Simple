# Simple
练手的项目,写的不好的地方,请多指教

## 添加DialogUtils 工具类,可以定义按钮背景颜色,支持一个或多个按钮
DialogUtils.show(mActivity, "温馨提示", "这是测试", "#272727", "#46A3FF",
                getResources().getDrawable(R.drawable.shape_dialogtext_rightbg), true, new String[]{"不好", "好"}, null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().postSticky(new Msg("1"));
                        dialog.dismiss();
                    }
                });



## mvc和MVP架构都可以使用

## 网络框架使用retrofit和Rxjava,具体使用见http类