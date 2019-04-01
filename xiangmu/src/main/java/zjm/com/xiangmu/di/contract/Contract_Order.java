package zjm.com.xiangmu.di.contract;

import zjm.com.xiangmu.data.bean.Find_Bean;

public interface Contract_Order {
    //V层接口
    public interface View_Interface{
        //数据刷新
        public void showData(Find_Bean find_bean);
    }

    //P层接口
    public interface Presenter_Interface<View_Interface>{
        //绑定
        public void attahView(View_Interface view_interface);

        //解绑
        public void deathView(View_Interface view_interface);

        //给M层处理
        public void requestData(int userId, String sessionId, int status);
    }

    //M层接口
    public interface Model_Interface{
        //网络请求数据
        public void getJson(int userId, String sessionId, int status, CallBack_Order callBack_order);

        //接口回调
        public interface CallBack_Order{
            //将结果返回给P层
            public void responseData(Find_Bean find_bean);
        }
    }
}
