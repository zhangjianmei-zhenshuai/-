package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.ShangPinBean;
import zjm.com.xiangmu.data.bean.ShopBean;

public class MyAdapter extends BaseQuickAdapter<ShangPinBean.ResultBean.RxxpBean.CommodityListBean,BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<ShangPinBean.ResultBean.RxxpBean.CommodityListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, ShangPinBean.ResultBean.RxxpBean.CommodityListBean item) {
        //给名称赋值
        helper.setText( R.id.tv_rxxp,item.getCommodityName() );
        //给价格赋值
        helper.setText( R.id.tv_rxxp_price,"￥"+item.getPrice() );
        //查找图片并赋值
        ImageView img_rxxp = helper.getView( R.id.img_rxxp );
        Glide.with( mContext ).load( item.getMasterPic() ).into( img_rxxp );
    }
}
