package zjm.com.xiangmu.di.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.bean.Find_Bean;
import zjm.com.xiangmu.di.contract.ApiServices;
import zjm.com.xiangmu.di.contract.Contract_Order;

/*
* fragment-我的
* */

public class Model_Order implements Contract_Order.Model_Interface {
    @Override
    /*代付款*/
    public void getJson(int userId, String sessionId, int status, final CallBack_Order callBack_order) {
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getDaiFu( userId,sessionId,status,1,5 )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<Find_Bean>() {
                    @Override
                    public void accept(Find_Bean find_bean) throws Exception {
                        callBack_order.responseData(find_bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                } );
    }
}
