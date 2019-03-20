package zjm.com.xiangmu.di.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.Contract_Search;

public class Model_Search implements Contract_Search.Model_Interface {
    @Override
    public void getJson(String keyword, int page, int count, final CallBack_Search callBack_search) {
        OkHttpUtils.getGetJson( Constant.QUERY_URL + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_search.responseData(message);
            }
        } );
    }
}
