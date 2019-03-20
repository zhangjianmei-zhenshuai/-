package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.QueryBean;

public class QueryAdapter extends BaseQuickAdapter<QueryBean.ResultBean,BaseViewHolder> {
    public QueryAdapter(int layoutResId, @Nullable List<QueryBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryBean.ResultBean item) {
        helper.setText( R.id.tv_query_name,item.getCommodityName() );//name
        helper.setText( R.id.tv_query_price,"￥" + item.getPrice() );//价格
        helper.setText( R.id.tv_query_salenum,"已售" + item.getSaleNum()+"件" );//销量
        ImageView img_query = helper.getView( R.id.img_query );
        Glide.with( mContext ).load( item.getMasterPic() ).into( img_query );

    }
}
