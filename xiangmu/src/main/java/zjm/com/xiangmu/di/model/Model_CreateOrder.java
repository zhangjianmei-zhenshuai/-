package zjm.com.xiangmu.di.model;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zjm.com.xiangmu.data.Constant;
import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.di.contract.ApiServices;
import zjm.com.xiangmu.di.contract.Contract_createOrder;

public class Model_CreateOrder implements Contract_createOrder.Model_Interface {

    @Override
    public void getJson(int userId, String sessionId, String price, int address_id, String orderInfo, final CallBack_Creat callBack_creat) {
        //Retrofit请求+rxjava--创建订单
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getOrder( userId,sessionId,price,address_id,orderInfo )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<Order_Bean>() {
                    @Override
                    public void accept(Order_Bean order_bean) throws Exception {
                        callBack_creat.responseData( order_bean );
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i( "error" ,""+throwable );
                    }
                } );
    }

    @Override//支付
    public void getJson_pay(int userId, String sessionId, String orderId, int payType, final CallBack_Pay callBack_pay) {
        new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl( Constant.QUAN_URL )
                .build()
                .create( ApiServices.class )
                .getPay( userId,sessionId,orderId,payType )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Consumer<Pay_Bean>() {
                    @Override
                    public void accept(Pay_Bean pay_bean) throws Exception {
                        callBack_pay.responseData( pay_bean );
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i( "pay-------------" ,""+throwable);
                    }
                } );
    }
}
