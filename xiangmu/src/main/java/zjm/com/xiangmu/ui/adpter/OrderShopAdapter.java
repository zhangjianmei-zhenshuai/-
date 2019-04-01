package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Order_Shop_Bean;

/*
*   确认订单页面的商品信息
* */
public class OrderShopAdapter extends BaseQuickAdapter<Order_Shop_Bean,BaseViewHolder> {
    public OrderShopAdapter(int layoutResId, @Nullable List<Order_Shop_Bean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Order_Shop_Bean item) {
        helper.setText( R.id.tv_order_name,item.getCommodityName() );
        helper.setText( R.id.tv_order_price,item.getPrice());
        SimpleDraweeView img_order = helper.getView( R.id.img_order );
        img_order.setImageURI( item.getPic() );
    }
}
