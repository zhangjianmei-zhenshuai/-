package zjm.com.xiangmu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zjm.com.xiangmu.R;

public class Frag_dingdan extends Fragment {
    @BindView(R.id.radiogroup1)
    RadioGroup radiogroup1;
    @BindView(R.id.pager_ding)
    FrameLayout pager_ding;
    Unbinder unbinder;
    private FragmentManager manager;
    private Order_All order_all;
    private Order_Daifu order_daifu;
    private Order_Daiping order_daiping;
    private Order_Daishou order_daishou;
    private Order_Yiwan order_yiwan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.frag_dingdan, container, false );
        unbinder = ButterKnife.bind( this, view );
        //得到管理者类
        manager = getFragmentManager();
        //开启事物
        FragmentTransaction transaction = manager.beginTransaction();
        //添加事物
        order_all = new Order_All();
        order_daifu = new Order_Daifu();
        order_daiping = new Order_Daiping();
        order_daishou = new Order_Daishou();
        order_yiwan = new Order_Yiwan();
        transaction.add( R.id.pager_ding, order_all);
        transaction.add( R.id.pager_ding, order_daifu);
        transaction.add( R.id.pager_ding, order_daiping);
        transaction.add( R.id.pager_ding, order_daishou);
        transaction.add( R.id.pager_ding, order_yiwan);
        //默认选中第一个
        radiogroup1.check( radiogroup1.getChildAt( 0 ).getId() );
        //只显示第一个页面
        transaction.show( order_all ).hide( order_daifu ).hide( order_daiping ).hide( order_daishou ).hide( order_yiwan );
        transaction.commit();//提交事务

        //当我点击按钮时,页面切换
        radiogroup1.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //开启一个事物
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio6:
                        //显示对应页面
                        transaction1.show( order_all ).hide( order_daifu ).hide( order_daiping ).hide( order_daishou ).hide( order_yiwan );
                        break;
                    case R.id.radio7:
                        //显示对应页面
                        transaction1.show( order_daifu ).hide( order_all ).hide( order_daiping ).hide( order_daishou ).hide( order_yiwan );
                        break;
                    case R.id.radio8:
                        //显示对应页面
                        transaction1.show( order_daiping ).hide( order_daifu ).hide( order_all ).hide( order_daishou ).hide( order_yiwan );
                        break;
                    case R.id.radio9:
                        //显示对应页面
                        transaction1.show( order_daishou ).hide( order_daifu ).hide( order_daiping ).hide( order_all ).hide( order_yiwan );
                        break;
                    case R.id.radio10:
                        //显示对应页面
                        transaction1.show( order_yiwan ).hide( order_daifu ).hide( order_daiping ).hide( order_daishou ).hide( order_all );
                        break;
                }
                //提交事物
                transaction1.commit();
            }
        } );//当我点击按钮时
        return view;
    }


    @Override
    //ButterKnife
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
