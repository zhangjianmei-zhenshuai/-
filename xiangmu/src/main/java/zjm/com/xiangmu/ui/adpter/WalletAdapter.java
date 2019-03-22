package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.WalletBean;
import zjm.com.xiangmu.data.utils.TimeUtils;

public class WalletAdapter extends BaseQuickAdapter<WalletBean.ResultBean.DetailListBean,BaseViewHolder> {
    public WalletAdapter(int layoutResId, @Nullable List<WalletBean.ResultBean.DetailListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletBean.ResultBean.DetailListBean item) {
        helper.setText( R.id.tv_wallet_xiaofei,"￥"+item.getAmount() );//消费金额
        long time = item.getConsumerTime();
        String s = TimeUtils.longToDate( time );//将字符串转换成时间
        helper.setText( R.id.tv_wallet_time,s);//消费时间
    }
}
