package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.CommentBean;
import zjm.com.xiangmu.data.utils.TimeUtils;

public class CommentAdapter extends BaseQuickAdapter<CommentBean.ResultBean,BaseViewHolder> {
    public CommentAdapter(int layoutResId, @Nullable List<CommentBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.ResultBean item) {
        helper.setText( R.id.tv_comment_name,item.getNickName() );//name
        long createTime = item.getCreateTime();
        String s = TimeUtils.longToDate( createTime );
        helper.setText( R.id.tv_comment_date,s );//日期
        helper.setText( R.id.tv_comment,item.getContent());//内容
        SimpleDraweeView img_comment_tup = helper.getView( R.id.img_comment_tup );//评论图片
        img_comment_tup.setImageURI( item.getImage() );
        SimpleDraweeView img_conmment_toux = helper.getView( R.id.img_conmment_toux );//头像
        img_conmment_toux.setImageURI( item.getHeadPic() );

    }
}
