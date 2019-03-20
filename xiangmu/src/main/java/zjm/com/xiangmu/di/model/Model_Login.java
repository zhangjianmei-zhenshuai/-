package zjm.com.xiangmu.di.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.di.contract.Contract_Login;

    public class Model_Login implements Contract_Login.Login_Model_Interface {
        @Override
        public void getLoginJson(String phone, String pwd, CallBack_Login callBack_login) {
            //OKHttp的异步请求
            okHttp(phone,pwd,callBack_login);
        }

        //OKHttp的异步请求
        private void okHttp(String phone, String pwd, final CallBack_Login callBack_login) {
            //创建OKHttp的对象
            OkHttpClient build = new OkHttpClient.Builder().build();
            //空表头
            FormBody formBody = new FormBody.Builder().build();
            Request request = new Request.Builder()
                    .method( "POST",formBody )
                    .url( Constant.LOGIN_URL+"?phone="+phone+"&pwd="+pwd )
                    .build();
            Call call = build.newCall( request );
            call.enqueue( new Callback() {
                @Override
                //失败时
                public void onFailure(Call call, IOException e) {
                    String message = e.getMessage();
                    callBack_login.responseData(message);
                }

                @Override
                //成功时
                public void onResponse(Call call, Response response) throws IOException {
                    String message = response.body().string();
                    callBack_login.responseData( message );
                }
            } );
        }

}
