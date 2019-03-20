package zjm.com.xiangmu.di.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.Contract_Quan;

public class Model_Quan implements Contract_Quan.Quan_Model_Interface {
    @Override
    public void getJson(final CallBack_Quan callBack_quan) {
        //请求数据Get
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
        } );
    }


}
