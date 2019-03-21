package zjm.com.xiangmu.di.contract;

public interface Contract_My {
    //V层接口
    public interface View_Interface{
        public void showData(String message);//数据刷新
    }

    //P层接口
    public interface Presenter_Interface<View_Interface>{
        //绑定
        public void attahView(View_Interface view_interface);

        //解绑
        public void deathView(View_Interface view_interface);

        //把账号密码交给M层处理
        public void requestData(int userId, String sessionId);
    }

    //M层接口
    public interface Model_Interface{
        //网络请求数据
        public void getJson(int userId, String sessionId, CallBack_Wallet callBack_wallet);

        //接口回调
        public interface CallBack_Wallet{
            //将结果返回给P层
            public void responseData(String message);
        }
    }
}
