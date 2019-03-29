package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.di.contract.Contract_createOrder;
import zjm.com.xiangmu.di.model.Model_CreateOrder;

public class Presenter_CreateOrder implements Contract_createOrder.Presenter_Interface<Contract_createOrder.View_Interface> {
    Contract_createOrder.View_Interface view_interface;
    private Model_CreateOrder modle;

    @Override
    public void attahView(Contract_createOrder.View_Interface view_interface) {
        this.view_interface=view_interface;
        modle = new Model_CreateOrder();
    }

    @Override
    public void deathView(Contract_createOrder.View_Interface view_interface) {

    }

    @Override//创建订单
    public void requestData(int userId, String sessionId, String price, int address_id, String orderInfo) {
        modle.getJson( userId,sessionId,price,address_id,orderInfo,new Contract_createOrder.Model_Interface.CallBack_Creat() {
            @Override
            public void responseData(Order_Bean order_bean) {
                view_interface.showData(order_bean);
            }
        } );
    }

    @Override//支付
    public void requestData_pay(int userId, String sessionId, String orderId, int payType) {
        modle.getJson_pay( userId,sessionId,orderId,payType,new Contract_createOrder.Model_Interface.CallBack_Pay() {
            @Override
            public void responseData(Pay_Bean pay_bean) {
                view_interface.showData_Pay(pay_bean);
            }
        } );
    }
}
