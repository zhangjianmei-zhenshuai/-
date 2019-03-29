package zjm.com.xiangmu.di.contract;

import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;

/*
* 创建订单
* &&
* 支付
* */
public interface Contract_createOrder {
    //V层接口
    public interface View_Interface{
        public void showData(Order_Bean order_bean);//创建订单

        public void showData_Pay(Pay_Bean pay_bean);//支付
    }

    //P层接口
    public interface Presenter_Interface<View_Interface>{
        //绑定
        public void attahView(View_Interface view_interface);

        //解绑
        public void deathView(View_Interface view_interface);

        //创建订单
        public void requestData(int userId, String sessionId, String price, int address_id, String orderInfo);

        //支付
        public void requestData_pay(int userId, String sessionId, String orderId, int payType);

    }

    //M层接口
    public interface Model_Interface{
        //创建订单
        public void getJson(int userId, String sessionId, String price, int address_id, String orderInfo, CallBack_Creat callBack_creat);

        //支付
        public void getJson_pay(int userId, String sessionId, String orderId, int payType, CallBack_Pay callBack_pay);

        //接口回调--创建订单
        public interface CallBack_Creat{
            //将结果返回给P层
            public void responseData(Order_Bean order_bean);
        }

        //接口回调--支付
        public interface CallBack_Pay{
            //将结果返回给P层
            public void responseData(Pay_Bean pay_bean);
        }
    }
}
