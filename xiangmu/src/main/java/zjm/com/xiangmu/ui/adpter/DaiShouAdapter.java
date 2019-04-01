package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.Find_Bean;

public class DaiShouAdapter extends BaseQuickAdapter<Find_Bean.OrderListBean,BaseViewHolder> {
    public DaiShouAdapter(int layoutResId, @Nullable List<Find_Bean.OrderListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Find_Bean.OrderListBean item) {
        helper.setText( R.id.tv_daishou_orderId,item.getOrderId() );
        helper.setText( R.id.tv_daishou_expressCompName,"派件公司  "+item.getExpressCompName() );
        helper.setText( R.id.tv_daishou_expressSn,"快递单号   "+item.getExpressSn() );


        //helper.setText( R.id.tv_daifu_time,item.getUserId()+"" );

        /*
         * 代付款的子条目适配器
         * */
        RecyclerView rv_daishou_boy = helper.getView( R.id.rv_daishou_boy );
        DaiShouBoyAdapter adapter = new DaiShouBoyAdapter( R.layout.item_daishou_boy, item.getDetailList() );
        LinearLayoutManager manager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );
        rv_daishou_boy.setLayoutManager( manager );
        rv_daishou_boy.setAdapter( adapter );
    }
}
