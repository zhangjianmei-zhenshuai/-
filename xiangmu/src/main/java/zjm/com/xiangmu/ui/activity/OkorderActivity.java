package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.AddressBean;
import zjm.com.xiangmu.di.contract.Contract_My;
import zjm.com.xiangmu.di.presenter.Presenter_My;
import zjm.com.xiangmu.ui.adpter.AddressAdapter;

/*
 * 确认订单页面
 * */
public class OkorderActivity extends AppCompatActivity implements Contract_My.View_Interface {
    @BindView(R.id.img_order)
    SimpleDraweeView img_Order;//图片
    @BindView(R.id.tv_order_name)
    TextView tv_OrderName;//那么
    @BindView(R.id.tv_order_price)
    TextView tv_OrderPrice;//价格
    @BindView(R.id.tv_order_num)//底部付款价格
            TextView tv_OrderNum;
    @BindView(R.id.tv_order_commit)//提交按钮
    TextView tv_OrderCommit;
    @BindView(R.id.rv_order_address)//收获地址rv
    RecyclerView rv_OrderAddress;
    private int userId;
    private String sessionId;
    private String price;
    private Presenter_My presenter_my;
    private int address_id;
    private int commodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_okorder );
        ButterKnife.bind( this );

        //接收intent传值
        Intent intent = getIntent();
        userId = intent.getIntExtra( "userId", 1 );
        commodityId = intent.getIntExtra( "commodityId", 1 );//商品id
        sessionId = intent.getStringExtra( "sessionId" );
        String commodityName = intent.getStringExtra( "commodityName" );//name
        price = intent.getStringExtra( "price" );        //价格
        String pic = intent.getStringExtra( "pic" );//图片

        presenter_my = new Presenter_My();  //创建P层
        presenter_my.attahView( this );
        presenter_my.requestData_Address( userId, sessionId );//请求收货地址数据

        //赋值
        img_Order.setImageURI( pic );
        tv_OrderName.setText( commodityName );
        tv_OrderPrice.setText( "￥" + price );
        tv_OrderNum.setText( "共1件商品,需付款" + price + "元" );//需付款金额


    }

    @Override
    public void showData(String message) {

    }

    @Override
    public void showData_Foot(String message) {

    }

    @Override
    //收货地址
    public void showData_Address(final List<AddressBean.ResultBean> message) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( OkorderActivity.this, ""+address, Toast.LENGTH_SHORT ).show();
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( OkorderActivity.this, LinearLayoutManager.VERTICAL, false );
                rv_OrderAddress.setLayoutManager( layoutManager );
                //设置适配器
                AddressAdapter addressAdapter = new AddressAdapter( R.layout.item_address, message );
                rv_OrderAddress.setAdapter( addressAdapter );

                //适配器点击事件 拿到快递id
                addressAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CheckBox cb_address = view.findViewById( R.id.cb_address );
                        cb_address.setChecked( true );
                        //快递id
                        address_id = message.get( position ).getId();
                        //Toast.makeText( OkorderActivity.this, "id"+address_id, Toast.LENGTH_SHORT ).show();
                    }
                } );//适配器点击事件 拿到快递id
            }
        } );
    }//收货地址

    //点击事件
    @OnClick(R.id.tv_order_commit)
    public void onViewClicked() {
        //点击去结算  调到支付页面
        Intent intent = new Intent( OkorderActivity.this, PayActivity.class );
        intent.putExtra( "price", price );   //价格
        intent.putExtra( "address_id", address_id );//收货id
        intent.putExtra( "sessionId", sessionId );
        intent.putExtra( "userId", userId );
        intent.putExtra( "commodityId", commodityId );
        startActivity( intent );
    }//点击事件


}
