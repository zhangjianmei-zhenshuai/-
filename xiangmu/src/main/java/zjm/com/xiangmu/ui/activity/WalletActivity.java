package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.WalletBean;
import zjm.com.xiangmu.di.contract.Contract_My;
import zjm.com.xiangmu.di.presenter.Presenter_My;
import zjm.com.xiangmu.ui.adpter.WalletAdapter;

public class WalletActivity extends AppCompatActivity implements Contract_My.View_Interface {

    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.rv_wallet)
    RecyclerView rv_wallet;
    @BindView(R.id.tv_wallet_jilu)
    TextView tv_wallet_jilu;
    private Presenter_My presenter_my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_wallet );
        ButterKnife.bind( this );
        Intent intent = getIntent();
        String sessionId = intent.getStringExtra( "sessionId" );//得到用户登录凭证
        int userId = intent.getIntExtra( "userId", 1 );//得到用户id
        Log.i( "sessionId", sessionId );
        Log.i( "userId", userId + "" );


        //新建P层
        presenter_my = new Presenter_My();
        presenter_my.attahView( this );//绑定
        presenter_my.requestData( userId, sessionId );//请求数据
        tv_wallet_jilu.setVisibility( View.GONE );//默认隐藏没有记录的页面
    }

    @Override
    //返回数据
    public void showData(final String message) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( WalletActivity.this, ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                WalletBean walletBean = gson.fromJson( message, WalletBean.class );
                WalletBean.ResultBean result = walletBean.getResult();
                int balance = result.getBalance();//当前余额
                tv_money.setText( "" + balance );//给余额赋值
                List<WalletBean.ResultBean.DetailListBean> wallet_list = result.getDetailList();
                //如果没有消费记录  就显示没有消费记录的页面
                if (wallet_list.size() == 0) {
                    rv_wallet.setVisibility( View.GONE );//隐藏rv
                    tv_wallet_jilu.setVisibility( View.VISIBLE );//显示没有数据的页面
                } else {
                    tv_wallet_jilu.setVisibility( View.GONE );//隐藏rv没有数据的页面
                    rv_wallet.setVisibility( View.VISIBLE );//显示没有数据的页面rv
                }

                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( WalletActivity.this, LinearLayoutManager.VERTICAL, false );
                rv_wallet.setLayoutManager( layoutManager );
                //设置适配器
                WalletAdapter walletAdapter = new WalletAdapter( R.layout.item_wallet, wallet_list );
                rv_wallet.setAdapter( walletAdapter );

            }
        } );
    }
}
