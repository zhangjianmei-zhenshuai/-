package zjm.com.xiangmu.di.model;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
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
}
