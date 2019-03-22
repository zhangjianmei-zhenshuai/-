package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.FootBean;
import zjm.com.xiangmu.di.contract.Contract_My;
import zjm.com.xiangmu.di.presenter.Presenter_My;
import zjm.com.xiangmu.ui.adpter.FootAdapter;
import zjm.com.xiangmu.ui.adpter.WalletAdapter;

public class FootActivity extends AppCompatActivity implements Contract_My.View_Interface {

    @BindView(R.id.rv_foot)
    RecyclerView rv_foot;
    private Presenter_My presenter_my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_foot );
        ButterKnife.bind( this );
        Intent intent = getIntent();
        String sessionId = intent.getStringExtra( "sessionId" );//得到用户登录凭证
        int userId = intent.getIntExtra( "userId", 1 );//得到用户id

        //新建P层
        presenter_my = new Presenter_My();
        presenter_my.attahView( this );
        presenter_my.requestData_Foot( userId, sessionId );

    }


    @Override
    //foot
    public void showData_Foot(final String message) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( FootActivity.this, ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                FootBean footBean = gson.fromJson( message, FootBean.class );
                List<FootBean.ResultBean> foot_list = footBean.getResult();//足迹数据源

                if (foot_list.size()==0) {//如果没有足迹
                    Toast.makeText( FootActivity.this, "当前还没有足迹哦", Toast.LENGTH_SHORT ).show();
                }

                //设置布局管理器
                GridLayoutManager gridLayoutManager = new GridLayoutManager( FootActivity.this, 2 );
                //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL );
                rv_foot.setLayoutManager( gridLayoutManager );
                //设置适配器
                FootAdapter footAdapter = new FootAdapter( R.layout.item_foot,foot_list );
                rv_foot.setAdapter( footAdapter );
            }
        } );
    }

    @Override
    public void showData(String message) {

    }
}
