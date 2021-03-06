package zjm.com.xiangmu.di.contract;

import java.util.List;

import zjm.com.xiangmu.data.bean.AddressBean;

public interface Contract_My {
    //V层接口
    public interface View_Interface{
        public void showData(String message);//数据刷新--钱包

        public void showData_Foot(String message);//数据刷新--足迹

        public void showData_Address(List<AddressBean.ResultBean> message);//数据刷新--收获地址
    }

    //P层接口
    public interface Presenter_Interface<View_Interface>{
        //绑定
        public void attahView(View_Interface view_interface);

        //解绑
        public void deathView(View_Interface view_interface);

        //把账号密码交给M层处理
        public void requestData(int userId, String sessionId);

        //足迹
        public void requestData_Foot(int userId, String sessionId);

        //收获地址
        public void requestData_Address(int userId, String sessionId);
    }

    //M层接口
    public interface Model_Interface{
        //网络请求数据
        public void getJson(int userId, String sessionId, CallBack_Wallet callBack_wallet);

        //足迹
        public void getJson_Foot(int userId, String sessionId, CallBack_Foot callBack_foot);

        //收获地址
        public void getJson_Address(int userId, String sessionId, CallBack_Address callBack_address);

        //接口回调
        public interface CallBack_Wallet{
            //将结果返回给P层
            public void responseData(String message);
        }

        //接口回调--足迹
        public interface CallBack_Foot{
            //将结果返回给P层
            public void responseData(String message);
        }

        //接口回调--收货地址
        public interface CallBack_Address{
            //将结果返回给P层
            public void responseData(List<AddressBean.ResultBean> message);
        }
    }
}
