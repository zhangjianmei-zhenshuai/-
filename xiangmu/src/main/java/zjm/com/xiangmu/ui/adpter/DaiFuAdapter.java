package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;

public class DaiFuAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean,BaseViewHolder> {
    public DaiFuAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean item) {
        helper.setText( R.id.tv_daifu_orderId,item.getOrderId() );
        //helper.setText( R.id.tv_daifu_time,item.getUserId()+"" );

        /*
        * 代付款的子条目适配器
        * */
        RecyclerView rv_daifu_boy = helper.getView( R.id.rv_daifu_boy );
        daifuBoyAdapter daifuBoyAdapter = new daifuBoyAdapter( R.layout.item_daifu_boy, item.getDetailList() );
        LinearLayoutManager manager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );
        rv_daifu_boy.setLayoutManager( manager );
        rv_daifu_boy.setAdapter( daifuBoyAdapter );
    }
}
