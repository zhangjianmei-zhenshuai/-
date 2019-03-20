package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.di.contract.Contract_rxxp;
import zjm.com.xiangmu.di.model.Model_Rxxp;

public class Presenter_Rxxp implements Contract_rxxp.Rxxp_Presenter_Interface<Contract_rxxp.Rxxp_View_Interface> {
    Contract_rxxp.Rxxp_View_Interface rxxp_view_interface;
    private Model_Rxxp model_rxxp;

    @Override
    //绑定
    public void attahView(Contract_rxxp.Rxxp_View_Interface rxxp_view_interface) {
        this.rxxp_view_interface=rxxp_view_interface;
        //新建M层
        model_rxxp = new Model_Rxxp();
    }

    @Override
    //解绑
    public void deathView(Contract_rxxp.Rxxp_View_Interface rxxp_view_interface) {

    }

    @Override
    //交给M层
    public void requestRxxpData() {
        model_rxxp.getRxxpData( new Contract_rxxp.Rxxp_Model_Interface.CallBcak_Rxxp() {
            @Override
            public void responseRxxpData(String message) {
                rxxp_view_interface.showRxxpData(message);
            }
        } );
    }

    @Override
    public void requestLunData() {
        model_rxxp.getLunData( new Contract_rxxp.Rxxp_Model_Interface.CallBcak_Lun() {
            @Override
            public void responseLunData(String message) {
                rxxp_view_interface.showLunBoData(message);
            }
        } );
    }
}
