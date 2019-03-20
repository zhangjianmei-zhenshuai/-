package zjm.com.xiangmu.di.presenter;

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
}
