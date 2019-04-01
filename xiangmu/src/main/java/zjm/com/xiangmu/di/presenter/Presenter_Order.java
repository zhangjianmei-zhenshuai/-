package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.data.bean.Find_Bean;
import zjm.com.xiangmu.di.contract.Contract_Order;
import zjm.com.xiangmu.di.model.Model_Order;

public class Presenter_Order implements Contract_Order.Presenter_Interface<Contract_Order.View_Interface>{
Contract_Order.View_Interface view_interface;
    private Model_Order model_order;

    @Override
    public void attahView(Contract_Order.View_Interface view_interface) {
        this.view_interface=view_interface;
        model_order = new Model_Order();
    }

    @Override
    public void deathView(Contract_Order.View_Interface view_interface) {

    }

    @Override
    public void requestData(int userId, String sessionId, int status) {
        model_order.getJson( userId,sessionId,status,new Contract_Order.Model_Interface.CallBack_Order() {
            @Override
            public void responseData(Find_Bean find_bean) {
                view_interface.showData(find_bean);
            }
        } );
    }
}
