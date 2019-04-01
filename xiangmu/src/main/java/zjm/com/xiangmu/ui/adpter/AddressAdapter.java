package zjm.com.xiangmu.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.data.bean.AddressBean;

/*
* 收货地址的适配器
* */
public class AddressAdapter extends BaseQuickAdapter<AddressBean.ResultBean,BaseViewHolder> {
    public AddressAdapter(int layoutResId, @Nullable List<AddressBean.ResultBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean.ResultBean item) {
        helper.setText( R.id.tv_address_name,item.getRealName() );//name
        helper.setText( R.id.tv_address_address,item.getAddress() );//地址
        helper.setText( R.id.tv_address_zipCode,item.getZipCode() );//邮编
    }
}
