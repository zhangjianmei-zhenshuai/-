package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.data.bean.Cart_Bean;
import zjm.com.xiangmu.data.bean.Sync_Bean;
import zjm.com.xiangmu.di.contract.Contract_Detail;
import zjm.com.xiangmu.di.model.Model_Detail;

public class Presenter_Detail implements Contract_Detail.Detail_Presenter_Interface<Contract_Detail.Detail_View_Interface> {
    Contract_Detail.Detail_View_Interface detail_view_interface;
    private Model_Detail model_detail;

    @Override
    public void attahView(Contract_Detail.Detail_View_Interface detail_view_interface) {
        this.detail_view_interface=detail_view_interface;
        model_detail = new Model_Detail();
    }

    @Override
    public void deathView(Contract_Detail.Detail_View_Interface detail_view_interface) {

    }

    @Override
    public void requestData(int commodityId) {
        model_detail.getJson(commodityId, new Contract_Detail.Detail_Model_Interface.CallBack_Detail() {
            @Override
            public void responseData(String message) {
                detail_view_interface.showData(message);
            }
        } );
    }

    @Override
    public void requestData_Comment(int commodityId, int page, int count) {
        model_detail.getJson_Comment( commodityId,page,count,new Contract_Detail.Detail_Model_Interface.CallBack_Comment() {
            @Override
            public void responseData(String message) {
                detail_view_interface.showData_Comment(message);
            }
        } );
    }

    @Override
    public void requestData_Cart(int userId, String sessionId) {
        model_detail.getJson_Cart( userId,sessionId,new Contract_Detail.Detail_Model_Interface.CallBack_Cart() {
            @Override
            public void responseData(Cart_Bean cart_bean) {
                detail_view_interface.showData_Cart(cart_bean);
            }
        } );
    }

    @Override
    public void requestData_Sync(int userId, String sessionId, String data) {
        model_detail.getJson_Sync( userId,sessionId,data,new Contract_Detail.Detail_Model_Interface.CallBack_Sync() {
            @Override
            public void responseData(Sync_Bean sync_bean) {
                detail_view_interface.showData_Sync(sync_bean);
            }
        } );
    }
}
