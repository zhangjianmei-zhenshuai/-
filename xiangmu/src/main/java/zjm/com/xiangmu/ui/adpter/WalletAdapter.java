package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.WalletBean;

public class WalletAdapter extends BaseQuickAdapter<WalletBean.ResultBean.DetailListBean,BaseViewHolder> {
    public WalletAdapter(int layoutResId, @Nullable List<WalletBean.ResultBean.DetailListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletBean.ResultBean.DetailListBean item) {
        helper.setText( R.id.tv_wallet_xiaofei,"￥"+item.getAmount() );//消费金额
        helper.setText( R.id.tv_wallet_time,""+item.getConsumerTime());//消费数据
    }
}
