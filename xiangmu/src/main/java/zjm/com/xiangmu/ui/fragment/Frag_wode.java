package zjm.com.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;
import zjm.com.xiangmu.ui.activity.AddressActivity;
import zjm.com.xiangmu.ui.activity.DataActivity;
import zjm.com.xiangmu.ui.activity.FootActivity;
import zjm.com.xiangmu.ui.activity.QuanActivity;
import zjm.com.xiangmu.ui.activity.WalletActivity;
/*
* fragment5
* 我的页面
* 个人资料  我的钱包  我的足迹  我的圈子  我的收货地址
* */
public class Frag_wode extends Fragment {
    @BindView(R.id.my_Wallet)
    LinearLayout my_wallet;//我的钱包
    Unbinder unbinder;
    @BindView(R.id.my_data)
    LinearLayout my_data;//个人资料
    @BindView(R.id.my_foot)
    LinearLayout my_foot;//我的足迹
    @BindView(R.id.my_Quan)
    LinearLayout my_quan;//我的圈子
    @BindView(R.id.my_Address)
    LinearLayout my_address;//我的收货地址
    private String sessionId;
    private int userId;
    private String headPic;
    private String nickName;
    private String pwd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_wode, container, false );
        unbinder = ButterKnife.bind( this, view );
        TextView tv_my_name = view.findViewById( R.id.tv_my_name );
        ImageView img_my_toux = view.findViewById( R.id.img_my_toux );
        Intent intent = getActivity().getIntent();
        headPic = intent.getStringExtra( "headPic" );        //得到头像
        nickName = intent.getStringExtra( "nickName" );        //得到name
        sessionId = intent.getStringExtra( "sessionId" );//得到用户登录凭证
        pwd = intent.getStringExtra( "pwd" );        //得到用户密码
        userId = intent.getIntExtra( "userId", 1 );//得到用户id

        tv_my_name.setText( nickName );//给name赋值
        Glide.with( getActivity() ).load( headPic ).into( img_my_toux );//给头像赋值

        return view;
    }


    @Override
    //ButterKnife
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //点击事件
    @OnClick({R.id.my_data, R.id.my_Wallet, R.id.my_foot,R.id.my_Quan, R.id.my_Address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_data://个人资料
                Intent intent1 = new Intent( getActivity(), DataActivity.class );
                intent1.putExtra( "headPic", headPic );//头像
                intent1.putExtra( "nickName", nickName );//name
                intent1.putExtra( "pwd", pwd );//密码待传
                startActivity( intent1 );
                break;
            case R.id.my_Wallet://我的钱包
                Intent intent = new Intent( getActivity(), WalletActivity.class );
                intent.putExtra( "sessionId", sessionId );
                intent.putExtra( "userId", userId );
                startActivity( intent );
                break;
            case R.id.my_foot://我的足迹  userId  sessionId
                Intent intent2 = new Intent( getActivity(), FootActivity.class );
                intent2.putExtra( "sessionId", sessionId );
                intent2.putExtra( "userId", userId );
                startActivity( intent2 );
                break;
            case R.id.my_Quan://我的圈子
                Intent intent4 = new Intent( getActivity(), QuanActivity.class );
                intent4.putExtra( "sessionId", sessionId );
                intent4.putExtra( "userId", userId );
                startActivity( intent4 );
                break;
            case R.id.my_Address://我的收货地址
                Intent intent3 = new Intent( getActivity(), AddressActivity.class );
                intent3.putExtra( "sessionId", sessionId );
                intent3.putExtra( "userId", userId );
                startActivity( intent3 );
                break;
        }
    }//点击事件

}
