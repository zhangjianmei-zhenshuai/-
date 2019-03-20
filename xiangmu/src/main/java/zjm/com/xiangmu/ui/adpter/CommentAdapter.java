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
import zjm.com.xiangmu.data.bean.CommentBean;

public class CommentAdapter extends BaseQuickAdapter<CommentBean.ResultBean,BaseViewHolder> {
    public CommentAdapter(int layoutResId, @Nullable List<CommentBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.ResultBean item) {
        helper.setText( R.id.tv_comment_name,item.getNickName() );//name
        helper.setText( R.id.tv_comment_date,item.getCreateTime()+"" );//日期
        helper.setText( R.id.tv_comment,item.getContent());//内容
        ImageView img_comment_tup = helper.getView( R.id.img_comment_tup );//评论图片
        ImageView img_conmment_toux = helper.getView( R.id.img_conmment_toux );//头像
        Glide.with( mContext ).load( item.getHeadPic() ).into( img_conmment_toux );
        Glide.with( mContext ).load( item.getImage() ).into( img_comment_tup );

    }
}
