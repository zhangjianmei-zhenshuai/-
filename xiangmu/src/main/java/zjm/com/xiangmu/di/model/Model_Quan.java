package zjm.com.xiangmu.di.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.bean.QuanziBean;
import zjm.com.xiangmu.di.contract.ApiServices;
import zjm.com.xiangmu.di.contract.Contract_Quan;

public class Model_Quan implements Contract_Quan.Quan_Model_Interface {
    @Override
    public void getJson(int userId, String sessionId, final CallBack_Quan callBack_quan) {
        /*//请求数据Get
        String url=Constant.QUZNZI_URL+"?page="+1+"&count="+5;
        OkHttpUtils.getGetJson( url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_quan.responseData(message);
            }
        } );*/

        //Retrofit请求
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getQuanZi( userId,sessionId,1,20 )
                .enqueue( new Callback<QuanziBean>() {
                    @Override
                    public void onResponse(Call<QuanziBean> call, Response<QuanziBean> response) {
                        List<QuanziBean.ResultBean> quan_list = response.body().getResult();
                        callBack_quan.responseData( quan_list );
                    }

                    @Override
                    public void onFailure(Call<QuanziBean> call, Throwable t) {

                    }
                } );
    }


}
