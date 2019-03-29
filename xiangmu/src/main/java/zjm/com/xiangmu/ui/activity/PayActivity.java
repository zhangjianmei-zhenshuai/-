package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.OrderInfo_Bean;
import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.di.contract.Contract_createOrder;
import zjm.com.xiangmu.di.presenter.Presenter_CreateOrder;

/*
* 支付页面  (选择付款方式)
* 微信支付  支付宝支付 余额支付
* */
public class PayActivity extends AppCompatActivity implements Contract_createOrder.View_Interface {

    private Presenter_CreateOrder presenter;
    private ArrayList<OrderInfo_Bean> info_list;
    private String orderId;
    private int payType=1;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pay );

        info_list = new ArrayList<>();//集合
        //接收intent传值
        Intent intent = getIntent();
        userId = intent.getIntExtra( "userId", 1 );
        sessionId = intent.getStringExtra( "sessionId" );
        int commodityId = intent.getIntExtra( "commodityId", 1 );//商品id
        String price = intent.getStringExtra( "price" );//价格
        int address_id = intent.getIntExtra( "address_id", 1 );//快递id
        info_list.add( new OrderInfo_Bean( commodityId,1 ) );

        Gson gson = new Gson();
        String orderInfo = gson.toJson( info_list );

        /*Toast.makeText( this, "userid:"+userId, Toast.LENGTH_SHORT ).show();
        Toast.makeText( this, "sessionId:"+sessionId, Toast.LENGTH_SHORT ).show();
        Toast.makeText( this, "价格:"+price, Toast.LENGTH_SHORT ).show();
        Toast.makeText( this, "快递id:"+address_id, Toast.LENGTH_SHORT ).show();
        Toast.makeText( this, "商品id:"+commodityId, Toast.LENGTH_SHORT ).show();*/



        presenter = new Presenter_CreateOrder();//创建P层
        presenter.attahView( this );
        presenter.requestData( userId, sessionId,price,address_id,orderInfo);// 请求创建订单



    }


    @Override
    //创建订单完成
    public void showData(final Order_Bean order_bean) {

        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //订单id
                orderId = order_bean.getOrderId();
                presenter.requestData_pay(userId,sessionId,orderId,payType);
                Toast.makeText( PayActivity.this, ""+order_bean.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    //支付完成
    public void showData_Pay(final Pay_Bean pay_bean) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                Toast.makeText( PayActivity.this, ""+pay_bean.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
