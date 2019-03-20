package zjm.com.xiangmu.di.contract;

public interface Contract_Login {
    //V层接口
    public interface Login_View_Interface{
        //数据刷新
        public void showLoginData(String message);
    }

    //P层接口
    public interface Login_Presenter_Interface<Login_View_Interface>{
        //绑定
        public void attahView(Login_View_Interface login_view_interface);

        //解绑
        public void deathView(Login_View_Interface login_view_interface);

        //把账号密码交给M层处理
        public void requestData(String phone, String pwd);
    }

    //M层接口
    public interface Login_Model_Interface{
        //网络请求数据
        public void getLoginJson(String phone, String pwd, CallBack_Login callBack_login);

        //接口回调
        public interface CallBack_Login{
            //将结果返回给P层
            public void responseData(String message);
        }
    }
}
