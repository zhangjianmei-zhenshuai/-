package zjm.com.xiangmu.di.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.Contract_rxxp;

public class Model_Rxxp implements Contract_rxxp.Rxxp_Model_Interface {
    @Override
    public void getRxxpData(CallBcak_Rxxp callBcak_rxxp) {
        //OKHttp请求--热销新品
        okhttp(callBcak_rxxp);
    }

    @Override
    public void getLunData(CallBcak_Lun callBcak_lun) {
        //OKHttp请求--轮播图
        okhttp_LunBo(callBcak_lun);
    }

    private void okhttp_LunBo(final CallBcak_Lun callBcak_lun) {
        OkHttpUtils.getGetJson( Constant.BANNER_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage();
                callBcak_lun.responseLunData(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBcak_lun.responseLunData( message );
            }
        } );
    }


    //OKHttp请求--热销新品
    private void okhttp(final CallBcak_Rxxp callBcak_rxxp) {
        String url=Constant.SHOP_URL;
        OkHttpUtils.getGetJson(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBcak_rxxp.responseRxxpData( message );
            }
        } );






        /*//创建日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i( "zjm",message );
            }
        } );
        //设置拦截器模式
        interceptor.setLevel( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor( interceptor )
                .build();
        Request request = new Request.Builder()
                .url( Constant.SHOP_URL )
                .build();
        Call call = build.newCall( request );
        call.enqueue( new Callback() {
            @Override
            //失败
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage();
                callBcak_rxxp.responseRxxpData(message);
            }

            @Override
            //成功
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBcak_rxxp.responseRxxpData(message);
            }
        } );*/
    }


}
