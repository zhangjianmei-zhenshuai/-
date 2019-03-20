package zjm.com.xiangmu.di.presenter;

import zjm.com.xiangmu.di.contract.Contract_Login;
import zjm.com.xiangmu.di.model.Model_Login;

public class Presenter_Login implements Contract_Login.Login_Presenter_Interface<Contract_Login.Login_View_Interface> {
    Contract_Login.Login_View_Interface login_view_interface;
    private Contract_Login.Login_Model_Interface model_login;

    @Override
    //绑定
    public void attahView(Contract_Login.Login_View_Interface login_view_interface) {
        this.login_view_interface=login_view_interface;
        //新建M层
        model_login = new Model_Login();
    }

    @Override
    //解绑
    public void deathView(Contract_Login.Login_View_Interface login_view_interface) {

    }

    @Override
    //账号密码交给M层
    public void requestData(String phone, String pwd) {
        model_login.getLoginJson( phone,pwd, new Contract_Login.Login_Model_Interface.CallBack_Login() {
            @Override
            public void responseData(String message) {
                //将数据返回给V层
                login_view_interface.showLoginData(message);
            }
        } );
    }
}
