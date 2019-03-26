package zjm.com.xiangmu.ui.fragment;

import android.content.Context;
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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.ShangPinBean;
import zjm.com.xiangmu.data.bean.ShopBean;
import zjm.com.xiangmu.di.contract.Contract_rxxp;
import zjm.com.xiangmu.di.presenter.Presenter_Rxxp;
import zjm.com.xiangmu.ui.activity.GoodDetailsActivity;
import zjm.com.xiangmu.ui.activity.SearchActivity;
import zjm.com.xiangmu.ui.adpter.MlssAdapter;
import zjm.com.xiangmu.ui.adpter.MyAdapter;
import zjm.com.xiangmu.ui.adpter.PzshAdapter;

public class Frag_home extends Fragment implements Contract_rxxp.Rxxp_View_Interface {

    @BindView(R.id.img_sousuo)
    ImageView img_sousuo;
    Unbinder unbinder;
    private RecyclerView rv;
    private RecyclerView rv_mlss;
    private RecyclerView rv_pzsh;
    private MZBannerView banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_home, container, false );
        //查找控件
        rv = view.findViewById( R.id.rv );
        rv_mlss = view.findViewById( R.id.rv_mlss );
        rv_pzsh = view.findViewById( R.id.rv_pzsh );
        banner = view.findViewById( R.id.banner );


        //当我进入到此页面时,就开始请示网络数据
        //新建P层
        Contract_rxxp.Rxxp_Presenter_Interface presenter_rxxp = new Presenter_Rxxp();
        //绑定
        presenter_rxxp.attahView( this );
        presenter_rxxp.requestRxxpData();//請求商品數據
        presenter_rxxp.requestLunData();//請求輪播數據

        unbinder = ButterKnife.bind( this, view );
        return view;
    }

    @Override
    //数据刷新--商品
    public void showRxxpData(final String message) {
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //解析数据
                Gson gson = new Gson();
                ShangPinBean shangPinBean = gson.fromJson( message, ShangPinBean.class );

                //数据源---热销新品
                final List<ShangPinBean.ResultBean.RxxpBean.CommodityListBean> rxxplist = shangPinBean.getResult().getRxxp().getCommodityList();
                //数据源---魔丽时尚
                final List<ShangPinBean.ResultBean.MlssBean.CommodityListBeanXX> mlsslist = shangPinBean.getResult().getMlss().getCommodityList();
                //数据源---品质生活
                final List<ShangPinBean.ResultBean.PzshBean.CommodityListBeanX> pzshlist = shangPinBean.getResult().getPzsh().getCommodityList();

                //=========================热销新品==================================
                //创建布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity(), LinearLayoutManager.HORIZONTAL, false );
                //设置布局管理器
                rv.setLayoutManager( layoutManager );
                //设置适配器
                MyAdapter adapter = new MyAdapter( R.layout.item_rxxp, rxxplist );
                rv.setAdapter( adapter );

                //条目点击事件
                adapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int commodityId = rxxplist.get( position ).getCommodityId();//得到商品id  跳转详情页面
                        Intent intent = new Intent( getContext(), GoodDetailsActivity.class );
                        intent.putExtra( "commodityId", commodityId );
                        startActivity( intent );
                    }
                } );

                //=========================魔丽时尚==================================
                //创建布局管理器
                LinearLayoutManager layoutManager1 = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
                //设置布局管理器
                rv_mlss.setLayoutManager( layoutManager1 );
                //设置适配器
                MlssAdapter mlssAdapter = new MlssAdapter( R.layout.item_mlsh, mlsslist );
                rv_mlss.setAdapter( mlssAdapter );

                //条目点击事件
                mlssAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int commodityId = mlsslist.get( position ).getCommodityId();//得到商品id  跳转详情页面
                        Intent intent = new Intent( getContext(), GoodDetailsActivity.class );
                        intent.putExtra( "commodityId", commodityId );
                        startActivity( intent );
                    }
                } );

                //=========================品质生活==================================
                //创建布局管理器
                GridLayoutManager layoutManager2 = new GridLayoutManager( getActivity(), 2 );
                //设置布局管理器
                rv_pzsh.setLayoutManager( layoutManager2 );
                //设置适配器
                PzshAdapter pzshAdapter = new PzshAdapter( R.layout.item_pzsh, pzshlist );
                rv_pzsh.setAdapter( pzshAdapter );

                //条目点击事件
                pzshAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int commodityId = pzshlist.get( position ).getCommodityId();//得到商品id  跳转详情页面
                        Intent intent = new Intent( getContext(), GoodDetailsActivity.class );
                        intent.putExtra( "commodityId", commodityId );
                        startActivity( intent );
                    }
                } );
            }
        } );
    }

    @Override
    //数据刷新--轮播
    public void showLunBoData(final String message) {
        getActivity().runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( getActivity(), ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                ShopBean bannerbean = gson.fromJson( message, ShopBean.class );
                List<ShopBean.ResultBean> banner_list = bannerbean.getResult();

                // 设置数据
                banner.setPages( banner_list, new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                } );

            }
        } );
    }




    //点击事件
    @OnClick({R.id.img_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_sousuo:
                Intent intent = new Intent( getActivity(), SearchActivity.class );
                startActivity(intent);
                break;
        }
    }


    public static class BannerViewHolder implements MZViewHolder<ShopBean.ResultBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from( context ).inflate( R.layout.banner_item, null );
            mImageView = (ImageView) view.findViewById( R.id.banner_image );
            return view;
        }

        @Override
        public void onBind(Context context, int i, ShopBean.ResultBean resultBean) {
            //glide加载图片
            Glide.with( context ).load( resultBean.getImageUrl() ).into( mImageView );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
