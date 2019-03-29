package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.AddressBean;
import zjm.com.xiangmu.di.contract.Contract_My;
import zjm.com.xiangmu.di.presenter.Presenter_My;
import zjm.com.xiangmu.ui.adpter.AddressAdapter;
import zjm.com.xiangmu.ui.adpter.CommentAdapter;
/*
* 收货地址页面
* */
public class AddressActivity extends AppCompatActivity implements Contract_My.View_Interface {

    @BindView(R.id.rv_address)
    RecyclerView rv_Address;
    private Presenter_My presenter_my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_address );
        ButterKnife.bind( this );

        Intent intent = getIntent();
        String sessionId = intent.getStringExtra( "sessionId" );//得到用户登录凭证
        int userId = intent.getIntExtra( "userId", 1 );//得到用户id

        //请求接口查询收货地址
        presenter_my = new Presenter_My();
        presenter_my.attahView( this );
        presenter_my.requestData_Address( userId, sessionId );
    }

    @Override
    //收货地址
    public void showData_Address(final List<AddressBean.ResultBean> address_list) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( AddressActivity.this, ""+address_list.size(), Toast.LENGTH_SHORT ).show();
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( AddressActivity.this, LinearLayoutManager.VERTICAL, false );
                rv_Address.setLayoutManager( layoutManager );
                //设置适配器
                AddressAdapter addressAdapter = new AddressAdapter( R.layout.item_address, address_list );
                rv_Address.setAdapter( addressAdapter );
            }
        } );
    }

    @Override
    public void showData(String message) {

    }

    @Override
    public void showData_Foot(String message) {

    }
}
