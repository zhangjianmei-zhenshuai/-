package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;

public class AllBoyAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean.DetailListBean,BaseViewHolder> {
    public AllBoyAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean.DetailListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean.DetailListBean item) {
        helper.setText( R.id.tv_all_name,item.getCommodityName() );
        helper.setText( R.id.tv_all_price,"￥"+item.getCommodityPrice() );
        SimpleDraweeView img_all_pic = helper.getView( R.id.img_all_pic );
        img_all_pic.setImageURI( item.getCommodityPic() );
    }
}
