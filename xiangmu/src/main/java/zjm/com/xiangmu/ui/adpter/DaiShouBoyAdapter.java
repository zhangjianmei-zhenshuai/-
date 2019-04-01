package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;

public class DaiShouBoyAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean.DetailListBean,BaseViewHolder> {
    public DaiShouBoyAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean.DetailListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean.DetailListBean item) {
        helper.setText( R.id.tv_daishou_name,item.getCommodityName() );
        helper.setText( R.id.tv_daishou_price,"￥"+item.getCommodityPrice() );
        SimpleDraweeView img_daishou_pic = helper.getView( R.id.img_daishou_pic );
        img_daishou_pic.setImageURI( item.getCommodityPic() );
    }
}
