package zjm.com.xiangmu.di.presenter;

import java.util.List;

import zjm.com.xiangmu.data.bean.QuanziBean;
import zjm.com.xiangmu.di.contract.Contract_Quan;
import zjm.com.xiangmu.di.model.Model_Quan;

public class Presenter_Quan implements Contract_Quan.Quan_Presenter_Interface<Contract_Quan.Quan_View_Interface> {
   Contract_Quan.Quan_View_Interface quan_view_interface;
    private Model_Quan model_quan;

    @Override
    public void attahView(Contract_Quan.Quan_View_Interface quan_view_interface) {
        this.quan_view_interface=quan_view_interface;
        model_quan = new Model_Quan();
    }

    @Override
    public void deathView(Contract_Quan.Quan_View_Interface quan_view_interface) {

    }

    @Override
    public void requestData(int userId, String sessionId) {
        model_quan.getJson( userId,sessionId,new Contract_Quan.Quan_Model_Interface.CallBack_Quan() {
            @Override
            public void responseData(List<QuanziBean.ResultBean> quan_list) {
                quan_view_interface.showData(quan_list);
            }
        });
    }
}
