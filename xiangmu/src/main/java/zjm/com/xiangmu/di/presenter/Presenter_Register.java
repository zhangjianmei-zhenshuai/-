package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.di.contract.Contract_Register;
import zjm.com.xiangmu.di.model.Model_Register;

public class Presenter_Register implements Contract_Register.Register_Presenter_Interface<Contract_Register.Register_View_Interface> {
    Contract_Register.Register_View_Interface register_view_interface;
    private Contract_Register.Register_Model_Interface model_register;

    @Override
    //绑定
    public void attahView(Contract_Register.Register_View_Interface register_view_interface) {
        this.register_view_interface=register_view_interface;
        //新建M层
        model_register = new Model_Register();
    }

    @Override
    //解绑
    public void deathView(Contract_Register.Register_View_Interface register_view_interface) {

    }

    @Override
    //数据交给M层处理
    public void requestRegisterData(String phone, String pwd) {
        model_register.getRegisterJson( phone,pwd,new Contract_Register.Register_Model_Interface.CallBack_Register() {
            @Override
            public void responseData(String message) {
                //数据回传给V层
                register_view_interface.showRegisterData( message );
            }
        } );
    }


}
