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
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;
import zjm.com.xiangmu.di.contract.Contract_Order;
import zjm.com.xiangmu.di.presenter.Presenter_Order;
import zjm.com.xiangmu.ui.adpter.AllOrderAdapter;
import zjm.com.xiangmu.ui.adpter.DaiFuAdapter;

/*
 * 全部订单
 * */
public class Order_All extends Fragment implements Contract_Order.View_Interface {
    @BindView(R.id.rv_all)
    RecyclerView rv_all;
    Unbinder unbinder;
    private int status = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.order_all, container, false );//绑定布局

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
                //Toast.makeText( getActivity(), "" + find_bean.getMessage(), Toast.LENGTH_SHORT ).show();
                List<Find_Bean.OrderListBean> list = find_bean.getOrderList();
                //设置布局管理器
                AllOrderAdapter allOrderAdapter = new AllOrderAdapter(R.layout.item_all,list);
                LinearLayoutManager manager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                rv_all.setLayoutManager( manager );
                rv_all.setAdapter( allOrderAdapter );
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
