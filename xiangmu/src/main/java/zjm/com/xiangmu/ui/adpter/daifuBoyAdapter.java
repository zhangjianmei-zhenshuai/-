package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;

/*
* 代付款子条目
* */
public class daifuBoyAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean.DetailListBean,BaseViewHolder> {
    public daifuBoyAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean.DetailListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean.DetailListBean item) {
        helper.setText( R.id.tv_daifu_name,item.getCommodityName() );
        helper.setText( R.id.tv_daifu_price,"￥"+item.getCommodityPrice() );
        SimpleDraweeView img_daifu_pic = helper.getView( R.id.img_daifu_pic );
        img_daifu_pic.setImageURI( item.getCommodityPic() );
    }
}
