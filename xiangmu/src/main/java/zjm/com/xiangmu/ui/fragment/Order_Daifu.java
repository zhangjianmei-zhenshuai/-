package zjm.com.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;
import zjm.com.xiangmu.di.contract.Contract_Order;
import zjm.com.xiangmu.di.presenter.Presenter_Order;
import zjm.com.xiangmu.ui.activity.FootActivity;
import zjm.com.xiangmu.ui.adpter.DaiFuAdapter;
import zjm.com.xiangmu.ui.adpter.FootAdapter;

/*
 * 待付款
 * */
public class Order_Daifu extends Fragment implements Contract_Order.View_Interface {

    @BindView(R.id.rv_daifu)
    RecyclerView rv_daifu;
    Unbinder unbinder;
    private Presenter_Order presenter;
    private int status=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.order_daifu, container, false );//绑定布局

        //得到intent传值
        Intent intent = getActivity().getIntent();
        int userId = intent.getIntExtra( "userId", 1 );
        String sessionId = intent.getStringExtra( "sessionId" );

        presenter = new Presenter_Order();//创建P层
        presenter.attahView( this );
        presenter.requestData( userId, sessionId ,status);


        unbinder = ButterKnife.bind( this, view );
        return view;
    }

    @Override
    //代付款
    public void showData(final Find_Bean find_bean) {
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                List<Find_Bean.OrderListBean> order_List = find_bean.getOrderList();
                //Toast.makeText( getActivity(), ""+order_List.get( 0 ).getOrderStatus(), Toast.LENGTH_SHORT ).show();
                //设置布局管理器
                DaiFuAdapter daiFuAdapter = new DaiFuAdapter(R.layout.item_daifu,order_List);
                LinearLayoutManager manager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                rv_daifu.setLayoutManager( manager );
                rv_daifu.setAdapter( daiFuAdapter );
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
