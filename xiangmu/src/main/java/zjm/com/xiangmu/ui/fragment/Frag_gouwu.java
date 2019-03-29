package zjm.com.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Cart_Bean;
import zjm.com.xiangmu.data.bean.OrderInfo_Bean;
import zjm.com.xiangmu.data.bean.Sync_Bean;
import zjm.com.xiangmu.di.contract.Contract_Detail;
import zjm.com.xiangmu.di.presenter.Presenter_Detail;
import zjm.com.xiangmu.ui.adpter.CartAdapter;
import zjm.com.xiangmu.ui.adpter.MyAdapter;
/*
 *fragment3
 * 购物车
 * */
public class Frag_gouwu extends Fragment implements Contract_Detail.Detail_View_Interface {
    @BindView(R.id.rv_cart)
    RecyclerView rv_Cart;
    Unbinder unbinder;
    @BindView(R.id.btn_account)
    TextView btn_Account;//去结算
    private Presenter_Detail presenter_detail;
    private Intent intent;
    private int userId;
    private String sessionId;
    public List<Cart_Bean.ResultBean> cart_list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_gouwu, container, false );
        unbinder = ButterKnife.bind( this, view );

        //注册eventbus
        EventBus.getDefault().register(this);
        //拿到带值跳转的值
        Intent intent = getActivity().getIntent();
        sessionId = intent.getStringExtra( "sessionId" ); //用户登录凭证
        userId = intent.getIntExtra( "userId", 65 );        //用户id

        //创建P层
        presenter_detail = new Presenter_Detail();
        presenter_detail.attahView( this );
        presenter_detail.requestData_Cart( userId,sessionId );//查询购物车

        return view;
    }


    @Override
    //商品详情
    public void showData(String message) {

    }

    @Override
    //商品评论
    public void showData_Comment(String message) {

    }

    @Override
    //查询购物车
    public void showData_Cart(final Cart_Bean cart_bean) {
        getActivity().runOnUiThread( new Runnable() {

            private CartAdapter cartadapter;

            @Override
            public void run() {
                //Toast.makeText( getActivity(), ""+cart_bean.getMessage(), Toast.LENGTH_SHORT ).show();
                //购物车数据源
                cart_list = cart_bean.getResult();
                //创建布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                //设置布局管理器
                rv_Cart.setLayoutManager( layoutManager );
                //设置适配器
                cartadapter = new CartAdapter( R.layout.item_cart, cart_list );
                rv_Cart.setAdapter( cartadapter );

                //点击删除
                cartadapter.setOnitemRemoveListener( new CartAdapter.OnitemRemoveListener() {
                    @Override
                    public void remove(int position) {
                        cart_list.remove( position );
                        cartadapter.notifyDataSetChanged();//刷新适配器
                        Toast.makeText( getContext(), "删除成功!", Toast.LENGTH_SHORT ).show();
                        StringBuffer stringBuffer = new StringBuffer(  );
                        //循环
                        stringBuffer.append( "[" );
                        for (int i = 0; i < cart_list.size(); i++) {
                            stringBuffer.append( "{\"commodityId\":"+cart_list.get( i ).getCommodityId()+",\"count\":"+cart_list.get( i ).getCount()+"}," );
                        }
                        stringBuffer.append( "]" );
                        presenter_detail.requestData_Sync( userId,sessionId,stringBuffer.toString() );
                    }
                } );//点击删除
            }
        } );
    }

    //接收消息EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(String msg){
        String s = msg.toString();//得到消息
        if (s.equals( "记得即时刷新" )) {
            presenter_detail.requestData_Cart( userId,sessionId );//查询购物车
        }
    }

    //反注册EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister( this );
    }

    @Override
    //同步购物车
    public void showData_Sync(Sync_Bean sync_bean) {
        Toast.makeText(getContext() , ""+sync_bean.getMessage(), Toast.LENGTH_SHORT ).show();
    }

    @Override
    //销毁时解绑
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_account)
    public void onViewClicked() {

    }
}
