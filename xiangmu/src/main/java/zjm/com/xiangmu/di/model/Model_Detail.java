package zjm.com.xiangmu.di.model;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.bean.Cart_Bean;
import zjm.com.xiangmu.data.bean.Sync_Bean;
import zjm.com.xiangmu.data.utils.OkHttpUtils;
import zjm.com.xiangmu.di.contract.ApiServices;
import zjm.com.xiangmu.di.contract.Contract_Detail;

public class Model_Detail implements Contract_Detail.Detail_Model_Interface {
    @Override
    //商品详情
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
    //商品评论
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

    @Override
    //查询购物车
    public void getJson_Cart(int userId, String sessionId, final CallBack_Cart callBack_cart) {
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getCart( userId,sessionId )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<Cart_Bean>() {
                    @Override
                    public void accept(Cart_Bean cart_bean) throws Exception {
                        callBack_cart.responseData(cart_bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                } );
    }

    @Override
    //同步购物车
    public void getJson_Sync(int userId, String sessionId, String data, final CallBack_Sync callBack_sync) {
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getSync( userId,sessionId,data )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<Sync_Bean>() {
                    @Override
                    public void accept(Sync_Bean sync_bean) throws Exception {
                        callBack_sync.responseData(sync_bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                } );
    }
}
