package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.FootBean;

public class FootAdapter extends BaseQuickAdapter<FootBean.ResultBean,BaseViewHolder> {
    public FootAdapter(int layoutResId, @Nullable List<FootBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, FootBean.ResultBean item) {
        ImageView img_foot_pic = helper.getView( R.id.img_foot_pic );
        Glide.with( mContext ).load( item.getMasterPic() ).into( img_foot_pic );//图片
        helper.setText( R.id.tv_foot_name,item.getCommodityName() );//name
        helper.setText( R.id.tv_foot_num,"以浏览"+item.getBrowseNum()+"次" );//次数
        helper.setText( R.id.tv_foot_price,"￥"+item.getPrice() );//价格
        helper.setText( R.id.tv_foot_time,item.getBrowseTime()+"" );//时间



    }
}
