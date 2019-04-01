package zjm.com.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;
import zjm.com.xiangmu.di.contract.Contract_Order;
import zjm.com.xiangmu.di.presenter.Presenter_Order;
import zjm.com.xiangmu.ui.adpter.DaiShouAdapter;

/*
 * 带收货
 * */
public class Order_Daishou extends Fragment implements Contract_Order.View_Interface {
    @BindView(R.id.rv_daishou)
    RecyclerView rv_daishou;
    Unbinder unbinder;
    private int status = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.order_daishou, container, false );//绑定布局

        //得到intent传值
        Intent intent = getActivity().getIntent();
        int userId = intent.getIntExtra( "userId", 1 );
        String sessionId = intent.getStringExtra( "sessionId" );

        Presenter_Order presenter = new Presenter_Order();
        presenter.attahView( this );
        presenter.requestData( userId, sessionId, status );

        unbinder = ButterKnife.bind( this, view );
        return view;
    }

    @Override
    public void showData(final Find_Bean find_bean) {
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                List<Find_Bean.OrderListBean> list = find_bean.getOrderList();
                //设置布局管理器
                DaiShouAdapter daiShouAdapter = new DaiShouAdapter( R.layout.item_daishou, list );
                LinearLayoutManager manager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                rv_daishou.setLayoutManager( manager );
                rv_daishou.setAdapter( daiShouAdapter );
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
