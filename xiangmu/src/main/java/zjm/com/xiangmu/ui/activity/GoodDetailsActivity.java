package zjm.com.xiangmu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Cart_Bean;
import zjm.com.xiangmu.data.bean.CommentBean;
import zjm.com.xiangmu.data.bean.DetailBean;
import zjm.com.xiangmu.data.bean.Order_Shop_Bean;
import zjm.com.xiangmu.data.bean.ShoppingBeanAdd;
import zjm.com.xiangmu.data.bean.Sync_Bean;
import zjm.com.xiangmu.di.contract.Contract_Detail;
import zjm.com.xiangmu.di.presenter.Presenter_Detail;
import zjm.com.xiangmu.ui.adpter.CommentAdapter;

/*
* 商品详情
* */

public class GoodDetailsActivity extends AppCompatActivity implements Contract_Detail.Detail_View_Interface {

    @BindView(R.id.img_detail_return)
    ImageView img_detail_return;//返回按钮
    @BindView(R.id.tv_detail_price)
    TextView tv_detail_price;//商品价格
    @BindView(R.id.tv_detail_sold)
    TextView tv_detail_sold;//已售
    @BindView(R.id.tv_detail_name)
    TextView tv_detail_name;//商品name
    @BindView(R.id.tv_detail_weight)
    TextView tv_detail_weight;//商品重量
    @BindView(R.id.detail_banner)
    MZBannerView detailBanner;//轮播
    @BindView(R.id.wv_detail)
    WebView wv_detail;//webview
    @BindView(R.id.tv_commentNum)
    TextView tv_commentnum;//评论总数
    @BindView(R.id.rv_commentnum)
    RecyclerView rv_comment;//商品评论列表
    @BindView(R.id.fab_jiaru)
    FloatingActionButton fab_jiaru;//悬浮球--加入购物车
    @BindView(R.id.fab_mai)
    FloatingActionButton fab_mai;//悬浮球--买
    @BindView(R.id.sv)
    ScrollView sv;//滑动ScrollView
    private Presenter_Detail presenter_detail;
    ArrayList tupian_list = new ArrayList<>();//图片集合
    private int page = 1;//页数
    private int count = 10;//总数
    private int userId;
    private String sessionId;
    private String[] split;
    private String commodityName;
    private String price;
    private String pic;
    private int commodityId;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gooddetails );
        ButterKnife.bind( this );

        //接收intent传值
        Intent intent = getIntent();
        userId = intent.getIntExtra( "userId", 1 );
        sessionId = intent.getStringExtra( "sessionId" );
        commodityId = intent.getIntExtra( "commodityId", 1 );

        presenter_detail = new Presenter_Detail();        //新建P层
        presenter_detail.attahView( this );        //绑定
        presenter_detail.requestData( commodityId );        //请求数据
        presenter_detail.requestData_Comment( commodityId, page, count );        //请求数据--商品评论

    }


    //点击返回按钮
    @OnClick(R.id.img_detail_return)
    public void onViewClicked() {
        finish();//关闭当前页面
    }


    @Override
    //刷新数据
    public void showData(final String message) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( GoodDetailsActivity.this, ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                DetailBean detailBean = gson.fromJson( message, DetailBean.class );
                DetailBean.ResultBean detail_list = detailBean.getResult();//数据源
                String details = detail_list.getDetails();//webview详情页面
                commodityName = detail_list.getCommodityName();
                price = detail_list.getPrice() + "";

                tv_detail_name.setText( detail_list.getCommodityName() );//给name赋值
                tv_detail_price.setText( "￥" + detail_list.getPrice() );//给价格赋值
                tv_detail_sold.setText( "已售" + detail_list.getSaleNum() + "件" );//销量
                tv_detail_weight.setText( detail_list.getWeight() + "kg" );//重量
                tv_commentnum.setText( "当前商品评论总数   " + detail_list.getCommentNum() );

                //加载webview商品详情
                WebSettings webSettings = wv_detail.getSettings();
                webSettings.setJavaScriptEnabled( true );//设置WebView属性，能够执行Javascript脚本
                webSettings.setBuiltInZoomControls( true ); //设置支持缩放
                String s2 = "<script type=\"text/javascript\">" +
                        "var imgs=document.getElementsByTagName('img');" +
                        "for(var i = 0; i<imgs.length; i++){" +
                        "imgs[i].style.width='100%';" +
                        "imgs[i].style.height='auto';" +
                        "}" +
                        "</script>";
                wv_detail.loadDataWithBaseURL( null, details + s2 + "<html><body>", "text/html", "utf-8", null );

                //设置轮播图片
                String picture = detail_list.getPicture();
                split = picture.split( "\\," );
                pic = split[0].toString();
                for (int i = 0; i < split.length; i++) {//循环添加图片
                    tupian_list.add( split[i] );
                }

                //给轮播设置数据
                detailBanner.setPages( tupian_list, new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                } );//给轮播设置数据
            }
        } );
    }

    @Override
    //刷新数据-商品评论
    public void showData_Comment(final String message) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                //Toast.makeText( GoodDetailsActivity.this, ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                CommentBean commentBean = gson.fromJson( message, CommentBean.class );
                List<CommentBean.ResultBean> comment_list = commentBean.getResult();//数据源

                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( GoodDetailsActivity.this, LinearLayoutManager.VERTICAL, false );
                rv_comment.setLayoutManager( layoutManager );
                //设置适配器
                CommentAdapter commentAdapter = new CommentAdapter( R.layout.item_comment, comment_list );
                rv_comment.setAdapter( commentAdapter );

            }
        } );
    }

    @Override
    //刷新数据-查询购物车
    public void showData_Cart(final Cart_Bean cart_bean) {
        runOnUiThread( new Runnable() {
            private List<Cart_Bean.ResultBean> cahxun_list;
            @Override
            public void run() {
                //Toast.makeText( GoodDetailsActivity.this, ""+cart_bean.getMessage(), Toast.LENGTH_SHORT ).show();
                cahxun_list = cart_bean.getResult();
                ArrayList<ShoppingBeanAdd> list = new ArrayList<>();
                for (int i = 0; i < cahxun_list.size(); i++) {
                    ShoppingBeanAdd beanAdd = new ShoppingBeanAdd( cahxun_list.get( i ).getCommodityId(), 1 );
                    list.add( beanAdd );
                }
                list.add( new ShoppingBeanAdd( commodityId,1 ) );
                Gson gson = new Gson();
                String data = gson.toJson( list );

                //请求同步购物车接口
                presenter_detail.requestData_Sync(userId,sessionId,data);
            }
        } );
    }

    @Override
    //同步购物车
    public void showData_Sync(final Sync_Bean sync_bean) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                Toast.makeText( GoodDetailsActivity.this, ""+sync_bean.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    //点击事件 --加入和购买
    @OnClick({R.id.fab_jiaru, R.id.fab_mai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_jiaru:
                //先查询一遍购物车
                presenter_detail.requestData_Cart(userId,sessionId);
                break;
            case R.id.fab_mai:
                //当我点击购买的时候  跳转到确认订单的页面
                Intent intent = new Intent( GoodDetailsActivity.this, OkorderActivity.class );
                intent.putExtra( "sessionId",sessionId );
                intent.putExtra( "userId",userId );
                intent.putExtra( "commodityId",commodityId );
                ArrayList<Order_Shop_Bean> shop_list = new ArrayList<>();
                shop_list.add( new Order_Shop_Bean( pic,commodityName,price ) );
                intent.putExtra( "shop_list",(Serializable) shop_list );
                startActivity( intent );
                finish();
                break;
        }
    }

    //轮播适配器
    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from( context ).inflate( R.layout.banner_item, null );
            mImageView = (ImageView) view.findViewById( R.id.banner_image );
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            //glide加载图片
            Glide.with( context ).load( s ).into( mImageView );
        }
    }//轮播适配器



}
