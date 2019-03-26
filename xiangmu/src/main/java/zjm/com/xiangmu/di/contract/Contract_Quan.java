package zjm.com.xiangmu.di.contract;

import java.util.List;

import zjm.com.xiangmu.data.bean.QuanziBean;

public interface Contract_Quan {
    //V层接口
    public interface Quan_View_Interface{
        //数据刷新
        public void showData(List<QuanziBean.ResultBean> message);
    }

    //P层接口
    public interface Quan_Presenter_Interface<Quan_View_Interface>{
        //绑定
        public void attahView(Quan_View_Interface quan_view_interface);

        //解绑
        public void deathView(Quan_View_Interface quan_view_interface);

        //给M层处理
        public void requestData(int userId, String sessionId);
    }

    //M层接口
    public interface Quan_Model_Interface{
        //网络请求数据
        public void getJson(int userId, String sessionId, CallBack_Quan callBack_quan);

        //接口回调
        public interface CallBack_Quan{
            //将结果返回给P层
            public void responseData(List<QuanziBean.ResultBean> message);
        }
    }
}
