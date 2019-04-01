package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.OrderInfo_Bean;
import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Order_Shop_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.di.contract.Contract_createOrder;
import zjm.com.xiangmu.di.presenter.Presenter_CreateOrder;

/*
 * 支付页面  (选择付款方式)
 * 微信支付  支付宝支付 余额支付
 * */
public class PayActivity extends AppCompatActivity implements Contract_createOrder.View_Interface {
    @BindView(R.id.btn_pay)
    Button btn_Pay;
    @BindView(R.id.item_pay_succeed)
    LinearLayout item_PaySucceed;
    @BindView(R.id.item_pay_error)
    LinearLayout item_PayError;
    @BindView(R.id.btn_back_home)
    Button btn_BackHome;
    @BindView(R.id.btn_watch_order)
    Button btn_WatchOrder;
    @BindView(R.id.btn_go_pay)
    Button btn_GoPay;
    @BindView(R.id.radio_ye)
    RadioButton radio_Ye;
    @BindView(R.id.radio_wx)
    RadioButton radio_Wx;
    @BindView(R.id.radio_zfb)
    RadioButton radio_Zfb;
    private Presenter_CreateOrder presenter;
    private ArrayList<OrderInfo_Bean> info_list;
    private String orderId;
    private int payType = 3;
    private int userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pay );
        ButterKnife.bind( this );

        info_list = new ArrayList<>();//集合
        //接收intent传值
        Intent intent = getIntent();
        userId = intent.getIntExtra( "userId", 1 );
        sessionId = intent.getStringExtra( "sessionId" );
        int price1 = intent.getIntExtra( "ordernum", 1 );//价格
        String price = price1 + "";
        int address_id = intent.getIntExtra( "address_id", 1 );//快递id
        //购物车集合
        List<Order_Shop_Bean> shop_list = (List<Order_Shop_Bean>) intent.getSerializableExtra( "shop_list" );
        for (int i = 0; i < shop_list.size(); i++) {
            int commodityId = shop_list.get( i ).getCommodityId();
            int size = shop_list.size();
            //Toast.makeText( this, "id"+shop_list.get( i ).getCommodityId(), Toast.LENGTH_SHORT ).show();
            info_list.add( new OrderInfo_Bean( commodityId, 1 ) );

        }

        btn_Pay.setText( "确认支付" + price );

        Gson gson = new Gson();
        String orderInfo = gson.toJson( info_list );//转为json串格式


        presenter = new Presenter_CreateOrder();//创建P层
        presenter.attahView( this );
        presenter.requestData( userId, sessionId, price, address_id, orderInfo );// 请求创建订单
        Log.i( "xxx", price + address_id + orderInfo );
    }


    @Override
    //创建订单完成
    public void showData(final Order_Bean order_bean) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //订单id
                orderId = order_bean.getOrderId();
                String order_status = order_bean.getStatus();
                Toast.makeText( PayActivity.this, "" + order_status + order_bean.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    //支付完成
    public void showData_Pay(final Pay_Bean pay_bean) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                String status = pay_bean.getStatus();//得到支付的状态码
                if (status.equals( "0000" )) {
                    //支付成功
                    //Toast.makeText( PayActivity.this, "" + pay_bean.getMessage(), Toast.LENGTH_SHORT ).show();
                    item_PaySucceed.setVisibility( View.VISIBLE );//显示成功的布局
                } else {
                    //支付失败
                    //Toast.makeText( PayActivity.this, "" + pay_bean.getMessage(), Toast.LENGTH_SHORT ).show();
                    item_PayError.setVisibility( View.VISIBLE );//显示失败的布局
                }
            }
        } );
    }//支付完成


    //点击事件
    @OnClick({R.id.btn_back_home, R.id.btn_watch_order, R.id.btn_go_pay, R.id.btn_pay,R.id.radio_ye, R.id.radio_wx, R.id.radio_zfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_ye://余额支付
                payType=1;
                //Toast.makeText( this, ""+payType, Toast.LENGTH_SHORT ).show();
                break;
            case R.id.radio_wx://微信
                payType=2;
                //Toast.makeText( this, ""+payType, Toast.LENGTH_SHORT ).show();
                break;
            case R.id.radio_zfb://支付宝
                payType=3;
                //Toast.makeText( this, ""+payType, Toast.LENGTH_SHORT ).show();
                break;
            case R.id.btn_pay://确认支付
                presenter.requestData_pay( userId, sessionId, orderId, payType );//请求支付
                break;
            case R.id.btn_back_home://返回主页
                item_PaySucceed.setVisibility( View.GONE );
                Intent intent = new Intent( PayActivity.this, ShouYeActivity.class );
                startActivity( intent );
                finish();//结束当前的页面
                break;
            case R.id.btn_watch_order://查看订单
                item_PaySucceed.setVisibility( View.GONE );
                Intent intent1 = new Intent( PayActivity.this, ShouYeActivity.class );
                startActivity( intent1 );
                finish();//结束当前的页面
                break;
            case R.id.btn_go_pay://继续支付
                item_PayError.setVisibility( View.GONE );
                break;
        }
    }//点击事件

}
