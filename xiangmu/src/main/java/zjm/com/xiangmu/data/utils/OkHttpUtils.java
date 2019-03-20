package zjm.com.xiangmu.data.utils;

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
import zjm.com.xiangmu.di.contract.Contract_rxxp;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils=null;

    public OkHttpUtils() {
        
    }

    //单例模式
    public static OkHttpUtils getInstance() {
        if (okHttpUtils==null){
            //同步锁
            synchronized (OkHttpUtils.class){
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        //返回
        return okHttpUtils;
    };

    //网络请求OkHttp--POST请求方式
    public static void getPOSTJson(String url, Callback callback){
        //创建拦截器拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i( "zjm",message );
            }
        } );
        //指定日志拦截器模式
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY );

        //创建OKHttp的对象
        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor( loggingInterceptor )
                .build();
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method( "POST",formBody )
                .url( url )
                .build();
        Call call = build.newCall( request );
        call.enqueue( callback );
    }


    //网络请求OkHttp--GET请求方式
    public static void getGetJson(String url, Callback callback){
        //创建OKHttp的对象
        OkHttpClient build = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url( url )
                .build();
        Call call = build.newCall( request );
        call.enqueue( callback );
    }

}
