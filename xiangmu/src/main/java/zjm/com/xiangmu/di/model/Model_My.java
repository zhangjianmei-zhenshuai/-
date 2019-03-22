package zjm.com.xiangmu.di.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.di.contract.Contract_My;

public class Model_My implements Contract_My.Model_Interface {
    @Override
    public void getJson(int userId, String sessionId, final CallBack_Wallet callBack_wallet) {
        OkGo.<String>get( Constant.WALLET_URL )
                .headers( "userId",userId+"" )
                .headers( "sessionId",sessionId )
                .params( "page",1 )
                .params( "count",10 )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String message = response.body().toString();
                        callBack_wallet.responseData( message );
                    }
                } );
    }

    @Override
    //foot
    public void getJson_Foot(int userId, String sessionId, final CallBack_Foot callBack_foot) {
        OkGo.<String>get( Constant.FOOT_URL )
                .headers( "userId",userId+"" )
                .headers( "sessionId",sessionId )
                .params( "page",1 )
                .params( "count",5 )
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String message = response.body().toString();
                        callBack_foot.responseData( message );
                    }
                } );
    }
}
