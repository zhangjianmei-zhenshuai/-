package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.QuanziBean;
import zjm.com.xiangmu.ui.activity.Clrcle;

public class QuanAdapter extends BaseQuickAdapter<QuanziBean.ResultBean,BaseViewHolder> {
    public QuanAdapter(int layoutResId, @Nullable List<QuanziBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, QuanziBean.ResultBean item) {
        ImageView img_quan_tu = helper.getView( R.id.img_quan_tu );
        Clrcle img_quan_toux = helper.getView( R.id.img_quan_toux );
        helper.setText( R.id.tv_quan,item.getContent() );//内容
        helper.setText( R.id.tv_quan_nickName,item.getNickName() );//用户昵称
        helper.setText( R.id.tv_quan_createTime,item.getCreateTime()+"");//发布时间
        helper.setText( R.id.tv_quan_prise,item.getGreatNum()+"");//点赞数
        Glide.with( mContext ).load( item.getHeadPic() ).into( img_quan_toux );//用户头像
        String picurl = item.getImage().toString();
        String[] split = picurl.split( "," );
        Glide.with( mContext ).load( split[0] ).into( img_quan_tu );//商品图片
    }
}
