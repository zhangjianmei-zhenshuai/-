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

public class MlssAdapter extends BaseQuickAdapter<ShangPinBean.ResultBean.MlssBean.CommodityListBeanXX,BaseViewHolder> {
    public MlssAdapter(int layoutResId, @Nullable List<ShangPinBean.ResultBean.MlssBean.CommodityListBeanXX> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, ShangPinBean.ResultBean.MlssBean.CommodityListBeanXX item) {
        //给文字赋值
        helper.setText( R.id.tv_mlss_name,item.getCommodityName());
        helper.setText( R.id.tv_mlss_price,"￥"+item.getPrice());
        //图片
        ImageView img_mlss = helper.getView( R.id.img_mlss );
        Glide.with( mContext ).load( item.getMasterPic() ).into( img_mlss );
    }
}
