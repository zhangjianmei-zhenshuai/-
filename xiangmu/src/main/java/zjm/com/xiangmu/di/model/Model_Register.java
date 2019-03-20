package zjm.com.xiangmu.di.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.Contract_Register;

public class Model_Register implements Contract_Register.Register_Model_Interface {
    @Override
    public void getRegisterJson(String phone, String pwd, CallBack_Register callBack_register) {
        //OKHttp异步请求数据
        okHttps(phone,pwd,callBack_register);
    }


    private void okHttps(String phone, String pwd, final CallBack_Register callBack_register) {
        String url=Constant.REGISTER_URL+"?phone="+phone+"&pwd="+pwd;
        /*OkHttpUtils.getPOSTJson( url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        } );*/
        //创建OKHttp的对象
        OkHttpClient build = new OkHttpClient.Builder() .build();
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method( "POST",formBody )
                .url( url )
                .build();
        Call call = build.newCall( request );
        call.enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_register.responseData( message );
            }
        } );
    }
}
