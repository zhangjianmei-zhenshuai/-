package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjm.com.xiangmu.R;
/*
* 个人资料
* */

public class DataActivity extends AppCompatActivity {
    @BindView(R.id.img_data_toux)
    SimpleDraweeView img_data_toux;
    @BindView(R.id.tv_data_name)
    TextView tv_data_name;
    @BindView(R.id.tv_data_pwd)
    TextView tv_data_pwd;//个人资料

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data );
        ButterKnife.bind( this );
        Intent intent = getIntent();
        String headPic = intent.getStringExtra( "headPic" );//得到头像
        String nickName = intent.getStringExtra( "nickName" );//得到name
        String pwd = intent.getStringExtra( "pwd" );//得到密码


        tv_data_name.setText(nickName);//给name赋值
        img_data_toux.setImageURI( headPic );//给头像赋值
        tv_data_pwd.setText( pwd );//给密码赋值
    }
}
