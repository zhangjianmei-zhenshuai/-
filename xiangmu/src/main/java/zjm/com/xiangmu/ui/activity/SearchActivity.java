package zjm.com.xiangmu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.QueryBean;
import zjm.com.xiangmu.di.contract.Contract_Search;
import zjm.com.xiangmu.di.presenter.Presenter_Search;
import zjm.com.xiangmu.ui.adpter.QueryAdapter;

public class SearchActivity extends AppCompatActivity implements Contract_Search.View_Interface {

    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.tv_sousuo)
    TextView tv_sousuo;
    @BindView(R.id.rv_search)
    RecyclerView rv_search;
    @BindView(R.id.item_meiy)
    LinearLayout item_meiy;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;//流式布局的控件搜索历史
    /*@BindView(R.id.img_trashion)
    ImageView img_trashion;//垃圾篓*/
    private Presenter_Search presenter;//P层
    private int page = 1;//页数1
    private int count = 5;//条数5
    private String keyword;//关键字
    private TextView textView;
    ArrayList<String> history_list = new ArrayList<>();//集合 存放历史记录
    private ArrayList<String> remen_list;//热门集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        ButterKnife.bind( this );

        presenter = new Presenter_Search();//创建P层
        presenter.attahView( this );//绑定

        item_meiy.setVisibility( View.GONE );//默认隐藏没有数据的页面
        smart.setEnableLoadMore( true );    //设置可以上拉加载
        smart.setEnableAutoLoadMore( false );//上拉加载具有弹性效果
    }

    @Override
    //数据展示
    public void showData(final String message) {
        runOnUiThread( new Runnable() {
            private QueryAdapter queryAdapter;
            @Override
            public void run() {
                Gson gson = new Gson();
                QueryBean queryBean = gson.fromJson( message, QueryBean.class );
                final List<QueryBean.ResultBean> query_list = queryBean.getResult();//数据源

                //如果没有数据  隐藏rv,展示没有数据的页面
                if (query_list.size() == 0) {
                    //Toast.makeText( SearchActivity.this, "没有数据哦", Toast.LENGTH_SHORT ).show();
                    smart.setVisibility( View.GONE );//隐藏rv控件
                    item_meiy.setVisibility( View.VISIBLE );//显示没有数据页面
                } else {
                    item_meiy.setVisibility( View.GONE );//隐藏没有数据页面
                    smart.setVisibility( View.VISIBLE );//显示rv
                }
                //设置布局管理器
                GridLayoutManager layoutManager = new GridLayoutManager( SearchActivity.this, 2 );
                rv_search.setLayoutManager( layoutManager );
                //设置适配器
                queryAdapter = new QueryAdapter( R.layout.item_query, query_list );
                rv_search.setAdapter( queryAdapter );

                //下拉刷新数据
                smart.setOnRefreshListener( new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        //进行一次网络请求数据
                        page = 1;
                        count = 5;
                        presenter.requestData( keyword, page, count );
                        queryAdapter.notifyDataSetChanged();//刷新适配器
                        smart.finishRefresh( true );
                        Toast.makeText( SearchActivity.this, "刷新成功", Toast.LENGTH_SHORT ).show();
                    }
                } );//下拉刷新数据

                //上拉加载更多数据
                smart.setOnLoadMoreListener( new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        count += 5;//当前页数据+1
                        //请求数据
                        presenter.requestData( keyword, page, count );
                        //刷新适配器
                        queryAdapter.notifyDataSetChanged();
                        Toast.makeText( SearchActivity.this, "刷新数据成功", Toast.LENGTH_SHORT ).show();
                        //刷新成功之后停止刷新
                        smart.finishLoadMore( true );
                    }
                } );//上拉加载更多数据

                //条目点击
                queryAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int commodityId = query_list.get( position ).getCommodityId();//商品id
                        //跳转到详情页面
                        Intent intent = new Intent( SearchActivity.this, GoodDetailsActivity.class );
                        intent.putExtra( "commodityId", commodityId );
                        startActivity( intent );
                    }
                } );//条目点击
            }
        } );
    }

    //点击事件
    @OnClick({R.id.img_back, R.id.tv_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back://点击返回finish
                finish();
                break;
            case R.id.tv_sousuo://点击搜索的时候
                keyword = ed_search.getText().toString();//得到输入框的值
                if (!keyword.equals( "" )) {
                    presenter.requestData( keyword, page, count );//请求数据
                    history_list.add( keyword );//输入框的值放到历史记录的集合中
                    ed_search.setText( null );//清空输入框的内容

                    //给流式布局设置适配器
                    flowlayout.setAdapter( new TagAdapter<String>( history_list ) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            textView = new TextView( SearchActivity.this );
                            //数据之间的距离
                            textView.setPadding( 5, 0, 5, 3 );
                            //字体大小
                            textView.setTextSize( 14 );
                            textView.setText( s );
                            return textView;
                        }
                    } );//给流式布局设置适配器

                    //给流式布局设置监听
                    flowlayout.setOnTagClickListener( new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            String s = history_list.get( position ).toString();
                            presenter.requestData( s, page, count );//请求数据
                            return false;
                        }
                    } );//给流式布局设置监听

                    //隐藏收起软键盘
                    View view1 = getWindow().peekDecorView();
                    if (view1 != null) {
                        InputMethodManager inputmanger = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
                        inputmanger.hideSoftInputFromWindow( view.getWindowToken(), 0 );
                    }
                } else {
                    Toast.makeText( this, "您输入的为空!", Toast.LENGTH_SHORT ).show();
                }
                break;
        }
    }//点击事件

}
