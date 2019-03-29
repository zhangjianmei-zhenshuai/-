package zjm.com.xiangmu.ui.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import zjm.com.xiangmu.R;

/*
* 自定义加减器
* */
public class Subtractor extends LinearLayout {
    private int i=1;		//初始化变量i
    private final TextView tv_num;


    public Subtractor(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        View view = View.inflate( context, R.layout.item_subtractor, this );
        Button btn_jian = view.findViewById(R.id.btn_jian);//减号
        Button btn_jia = view.findViewById(R.id.btn_jia);//加号
        tv_num = view.findViewById(R.id.tv_num);//数目
        tv_num.setText(i+"");

//点击减号
        btn_jian.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i>1){
                    i--;
                    tv_num.setText(i+"");
                } else {
                    Toast.makeText( getContext(), "数量不能小于1", Toast.LENGTH_SHORT ).show();
                }
            }
        } );//减号

//点击加号
        btn_jia.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                tv_num.setText(i+"");
            }
        } );//+号
    }
}
