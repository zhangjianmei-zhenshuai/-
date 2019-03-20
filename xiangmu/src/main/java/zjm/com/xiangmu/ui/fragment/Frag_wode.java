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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import zjm.com.xiangmu.R;

public class Frag_wode extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_wode, container, false );
        TextView tv_my_name = view.findViewById( R.id.tv_my_name );
        ImageView img_my_toux = view.findViewById( R.id.img_my_toux );
        Intent intent = getActivity().getIntent();
        String headPic = intent.getStringExtra( "headPic" );
        String nickName = intent.getStringExtra( "nickName" );
        tv_my_name.setText( nickName );//给name赋值
        Glide.with( getActivity() ).load( headPic ).into( img_my_toux );//给头像赋值


        return view;
    }
}
