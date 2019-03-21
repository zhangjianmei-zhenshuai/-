package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.di.contract.Contract_My;
import zjm.com.xiangmu.di.model.Model_My;

public class Presenter_My implements Contract_My.Presenter_Interface<Contract_My.View_Interface> {
    Contract_My.View_Interface view_interface;
    private Model_My model_my;

    @Override
    public void attahView(Contract_My.View_Interface view_interface) {
        this.view_interface=view_interface;
        model_my = new Model_My();
    }

    @Override
    public void deathView(Contract_My.View_Interface view_interface) {

    }

    @Override
    public void requestData(int userId, String sessionId) {
        model_my.getJson( userId,sessionId,new Contract_My.Model_Interface.CallBack_Wallet() {
            @Override
            public void responseData(String message) {
                view_interface.showData(message);
            }
        } );
    }
}