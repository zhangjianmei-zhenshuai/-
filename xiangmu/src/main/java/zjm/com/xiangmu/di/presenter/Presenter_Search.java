package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.di.contract.Contract_Search;
import zjm.com.xiangmu.di.model.Model_Search;

public class Presenter_Search implements Contract_Search.Presenter_Interface<Contract_Search.View_Interface> {
    Contract_Search.View_Interface view_interface;
    private Model_Search model_search;

    @Override
    public void attahView(Contract_Search.View_Interface view_interface) {
        this.view_interface=view_interface;
        model_search = new Model_Search();
    }

    @Override
    public void deathView(Contract_Search.View_Interface view_interface) {

    }

    @Override
    public void requestData(String keyword, int page, int count) {
        model_search.getJson( keyword,page,count,new Contract_Search.Model_Interface.CallBack_Search() {
            @Override
            public void responseData(String message) {
                view_interface.showData(message);
            }
        } );
    }
}
