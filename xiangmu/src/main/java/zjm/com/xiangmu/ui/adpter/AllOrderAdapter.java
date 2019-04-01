package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;
public class AllOrderAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean,BaseViewHolder> {
    public AllOrderAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean item) {
        helper.setText( R.id.tv_all_orderId,item.getOrderId());

        /*
         * 全部订单的子条目适配器
         * */
        RecyclerView rv_all_boy = helper.getView( R.id.rv_all_boy );
        AllBoyAdapter adapter = new AllBoyAdapter( R.layout.item_all_boy, item.getDetailList() );
        LinearLayoutManager manager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );
        rv_all_boy.setLayoutManager( manager );
        rv_all_boy.setAdapter( adapter );

    }
}
