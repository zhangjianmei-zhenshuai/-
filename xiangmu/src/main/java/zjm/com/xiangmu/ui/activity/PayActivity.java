package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.OrderInfo_Bean;
import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.di.contract.Contract_createOrder;
import zjm.com.xiangmu.di.presenter.Presenter_CreateOrder;
import zjm.com.xiangmu.ui.fragment.Frag_home;

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
    private Presenter_CreateOrder presenter;
    private ArrayList<OrderInfo_Bean> info_list;
    private String orderId;
    private int payType = 1;
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
        int commodityId = intent.getIntExtra( "commodityId", 1 );//商品id
        int price1 = intent.getIntExtra( "ordernum", 1 );//价格
        String price = price1 + "";
        int address_id = intent.getIntExtra( "address_id", 1 );//快递id

        info_list.add( new OrderInfo_Bean( commodityId, 1 ) );
        btn_Pay.setText( "确认支付" + price );

        Gson gson = new Gson();
        String orderInfo = gson.toJson( info_list );//转为json串格式


        presenter = new Presenter_CreateOrder();//创建P层
        presenter.attahView( this );
        presenter.requestData( userId, sessionId, price, address_id, orderInfo );// 请求创建订单
        Log.i( "xxx",price+address_id+orderInfo );
    }


    @Override
    //创建订单完成
    public void showData(final Order_Bean order_bean) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //订单id
                orderId = order_bean.getOrderId();
                Toast.makeText( PayActivity.this, "" + order_bean.getMessage(), Toast.LENGTH_SHORT ).show();
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
    @OnClick({R.id.btn_back_home, R.id.btn_watch_order, R.id.btn_go_pay,R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay://确认支付
                presenter.requestData_pay( userId, sessionId, orderId, payType );//请求支付
                break;
            case R.id.btn_back_home://返回主页
                item_PaySucceed.setVisibility( View.GONE );
                Intent intent = new Intent( PayActivity.this,ShouYeActivity.class );
                startActivity( intent );
                finish();//结束当前的页面
                break;
            case R.id.btn_watch_order://查看订单
                item_PaySucceed.setVisibility( View.GONE );
                Intent intent1 = new Intent( PayActivity.this,ShouYeActivity.class );
                startActivity( intent1 );
                finish();//结束当前的页面
                break;
            case R.id.btn_go_pay://继续支付
                item_PayError.setVisibility( View.GONE );
                break;
        }
    }//点击事件
}
