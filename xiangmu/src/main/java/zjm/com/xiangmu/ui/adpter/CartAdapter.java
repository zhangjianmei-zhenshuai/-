package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Cart_Bean;

/*
* 购物车列表适配器
* */
public class CartAdapter extends BaseQuickAdapter<Cart_Bean.ResultBean,BaseViewHolder> {
    private OnitemRemoveListener onitemRemoveListener;//声明接口
    public void setOnitemRemoveListener(OnitemRemoveListener onitemRemoveListener) {
        this.onitemRemoveListener = onitemRemoveListener;
    }
    public interface OnitemRemoveListener{//创建一个接口
        void remove(int position);
    }

    public CartAdapter(int layoutResId, @Nullable List<Cart_Bean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(final BaseViewHolder helper, Cart_Bean.ResultBean item) {
        SimpleDraweeView img_cart_pic = helper.getView( R.id.img_cart_pic );
        Button btn_delete = helper.getView( R.id.btn_delete );
        //点击删除
        btn_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = helper.getPosition();//得到下标
                onitemRemoveListener.remove(position);
            }
        } );
        img_cart_pic.setImageURI( item.getPic() );//图片
        helper.setText( R.id.tv_cart_name,item.getCommodityName() );//名称
        helper.setText( R.id.tv_cart_price,"￥"+item.getPrice() );//名称

    }
}
