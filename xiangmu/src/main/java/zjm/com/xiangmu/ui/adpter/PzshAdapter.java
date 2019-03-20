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

public class PzshAdapter extends BaseQuickAdapter<ShangPinBean.ResultBean.PzshBean.CommodityListBeanX,BaseViewHolder> {
    public PzshAdapter(int layoutResId, @Nullable List<ShangPinBean.ResultBean.PzshBean.CommodityListBeanX> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, ShangPinBean.ResultBean.PzshBean.CommodityListBeanX item) {
        //给文字赋值
        helper.setText( R.id.tv_pzsh_name,item.getCommodityName() );
        helper.setText( R.id.tv_pzsh_price,"￥"+item.getPrice() );
        //图片
        ImageView img_pzsh = helper.getView( R.id.img_pzsh );
        Glide.with( mContext ).load( item.getMasterPic() ).into( img_pzsh );
    }
}
