package zjm.com.xiangmu.di.contract;

public interface Contract_Detail {
    //V层接口
    public interface Detail_View_Interface{

        public void showData(String message);//数据刷新

        public void showData_Comment(String message);//商品评论
    }

    //P层接口
    public interface Detail_Presenter_Interface<Detail_View_Interface>{
        //绑定
        public void attahView(Detail_View_Interface detail_view_interface);

        //解绑
        public void deathView(Detail_View_Interface detail_view_interface);

        //把账号密码交给M层处理
        public void requestData(int commodityId);

        public void requestData_Comment(int commodityId, int page, int count); //商品评论
    }

    //M层接口
    public interface Detail_Model_Interface{
        //网络请求数据
        public void getJson(int commodityId, CallBack_Detail callBack_detail);

        public void getJson_Comment(int commodityId, int page, int count, CallBack_Comment callBack_comment);//商品评论
        //接口回调
        public interface CallBack_Detail{
            //将结果返回给P层
            public void responseData(String message);
        }

        //接口回调--商品评论
        public interface CallBack_Comment{
            //将结果返回给P层
            public void responseData(String message);
        }
    }
}
