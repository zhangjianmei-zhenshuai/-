package zjm.com.xiangmu.di.model;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.bean.AddressBean;
import zjm.com.xiangmu.di.contract.ApiServices;
import zjm.com.xiangmu.di.contract.Contract_My;

/*
* fragment-我的
* */

public class Model_My implements Contract_My.Model_Interface {
    @Override
    //我的钱包
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
    //foot足迹
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

    @Override
    //收货地址  Retrofit
    public void getJson_Address(final int userId, final String sessionId, final CallBack_Address callBack_address) {

        Log.i( "address_userId","userId:"+userId );
        Log.i( "address_sessionId","sessionId:"+sessionId );
        new Retrofit.Builder()
                .baseUrl( Constant.QUAN_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build()
                .create( ApiServices.class )
                .getAddress( userId,sessionId )
                .enqueue( new Callback<AddressBean>() {
                    @Override
                    public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                        List<AddressBean.ResultBean> address_list = response.body().getResult();
                        callBack_address.responseData( address_list );
                    }

                    @Override
                    public void onFailure(Call<AddressBean> call, Throwable t) {

                    }
                } );
    }
}
