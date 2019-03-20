package zjm.com.xiangmu.di.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.Contract_Detail;

public class Model_Detail implements Contract_Detail.Detail_Model_Interface {


    @Override
    public void getJson(int commodityId, final CallBack_Detail callBack_detail) {
        OkHttpUtils.getGetJson( Constant.XIANGQING_URL+"?commodityId="+commodityId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_detail.responseData(message);
            }
        } );
    }

    @Override
    public void getJson_Comment(int commodityId, int page, int count, final CallBack_Comment callBack_comment) {
        OkHttpUtils.getGetJson( Constant.COMMENT_URL+"?commodityId="+commodityId+"&page="+page+"&count="+count, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_comment.responseData(message);
            }
        } );
    }
}
