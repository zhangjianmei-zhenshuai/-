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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.QuanziBean;
import zjm.com.xiangmu.di.contract.Contract_Quan;
import zjm.com.xiangmu.di.presenter.Presenter_Quan;
import zjm.com.xiangmu.ui.adpter.QuanAdapter;

public class Frag_quanzi extends Fragment implements Contract_Quan.Quan_View_Interface {

    private Presenter_Quan presenter_quan;
    private RecyclerView rv_quan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_quanzi, container, false );
        rv_quan = view.findViewById( R.id.rv_quan );
        ImageView img_quan_prise = view.findViewById( R.id.img_quan_prise );//点赞的图片
        //拿到带值跳转的值
        Intent intent = getActivity().getIntent();
        String sessionId = intent.getStringExtra( "sessionId" );//用户登录凭证
        int userId = intent.getIntExtra( "userId", 65 );//用户id

        //新建P层 绑定 调用P层
        presenter_quan = new Presenter_Quan();
        presenter_quan.attahView( this );
        presenter_quan.requestData( );



        return view;
    }

    @Override
    //数据刷新
    public void showData(final String message) {
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( getActivity(), ""+message, Toast.LENGTH_SHORT ).show();
                //解析数据
                Gson gson = new Gson();
                QuanziBean quanziBean = gson.fromJson( message, QuanziBean.class );
                List<QuanziBean.ResultBean> list = quanziBean.getResult();
                //给rv_quan设置布局管理器  竖向
                LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                rv_quan.setLayoutManager( layoutManager );
                //设置适配器
                QuanAdapter quanAdapter = new QuanAdapter(R.layout.item_quan,list);
                rv_quan.setAdapter( quanAdapter );
            }
        } );
    }//数据刷新

}
