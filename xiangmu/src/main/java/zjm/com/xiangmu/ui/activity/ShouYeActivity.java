package zjm.com.xiangmu.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.ui.fragment.Frag_dingdan;
import zjm.com.xiangmu.ui.fragment.Frag_gouwu;
import zjm.com.xiangmu.ui.fragment.Frag_home;
import zjm.com.xiangmu.ui.fragment.Frag_quanzi;
import zjm.com.xiangmu.ui.fragment.Frag_wode;

public class ShouYeActivity extends AppCompatActivity {

    private Frag_home frag_home;
    private Frag_quanzi frag_quanzi;
    private Frag_gouwu frag_gouwu;
    private Frag_dingdan frag_dingdan;
    private Frag_wode frag_wode;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shou_ye );

        //查找控件
        RadioGroup radiogroup = (RadioGroup)findViewById( R.id.radiogroup );
        //得到管理者类
        manager = getSupportFragmentManager();
        //开启事物
        FragmentTransaction transaction = manager.beginTransaction();
        //添加事物
        frag_home = new Frag_home();
        frag_quanzi = new Frag_quanzi();
        frag_gouwu = new Frag_gouwu();
        frag_dingdan = new Frag_dingdan();
        frag_wode = new Frag_wode();
        transaction.add( R.id.pager, frag_home );
        transaction.add( R.id.pager, frag_quanzi );
        transaction.add( R.id.pager, frag_gouwu );
        transaction.add( R.id.pager, frag_dingdan );
        transaction.add( R.id.pager, frag_wode );
        //默认选中第一个按钮
        radiogroup.check( radiogroup.getChildAt( 0 ).getId() );
        //只显示第一个页面
        transaction.show( frag_home ).hide( frag_quanzi ).hide( frag_gouwu ).hide( frag_dingdan ).hide( frag_wode );
        //提交事物
        transaction.commit();

        //当我点击按钮时,页面切换
        radiogroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //开启一个事物
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio1:
                        //显示对应页面
                        transaction1.show( frag_home ).hide( frag_quanzi ).hide( frag_gouwu ).hide( frag_dingdan ).hide( frag_wode );
                        break;
                    case R.id.radio2:
                        //显示对应页面
                        transaction1.show( frag_quanzi ).hide( frag_home ).hide( frag_gouwu ).hide( frag_dingdan ).hide( frag_wode );
                        break;
                    case R.id.radio3:
                        //显示对应页面
                        transaction1.show( frag_gouwu ).hide( frag_quanzi ).hide( frag_home ).hide( frag_dingdan ).hide( frag_wode );
                        break;
                    case R.id.radio4:
                        //显示对应页面
                        transaction1.show( frag_dingdan ).hide( frag_quanzi ).hide( frag_gouwu ).hide( frag_home ).hide( frag_wode );
                        break;
                    case R.id.radio5:
                        //显示对应页面
                        transaction1.show( frag_wode ).hide( frag_quanzi ).hide( frag_gouwu ).hide( frag_dingdan ).hide( frag_home );
                        break;
                }
                //提交事物
                transaction1.commit();
            }
        } );


    }
}
