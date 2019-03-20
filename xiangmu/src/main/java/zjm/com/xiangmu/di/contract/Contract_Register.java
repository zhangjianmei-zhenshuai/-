package zjm.com.xiangmu.di.contract;

public interface Contract_Register {
    //V层接口
    public interface Register_View_Interface{
        //数据刷新
        public void showRegisterData(String message);
    }

    //P层接口
    public interface Register_Presenter_Interface<Register_View_Interface>{
        //绑定
        public void attahView(Register_View_Interface register_view_interface);

        //解绑
        public void deathView(Register_View_Interface register_view_interface);

        //把账号密码交给M层处理
        public void requestRegisterData(String phone, String pwd);
    }

    //M层接口
    public interface Register_Model_Interface{
        //网络请求数据
        public void getRegisterJson(String phone, String pwd, CallBack_Register callBack_register);

        //接口回调
        public interface CallBack_Register{
            //将结果返回给P层
            public void responseData(String message);
        }
    }
}
