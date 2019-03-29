package zjm.com.xiangmu.di.contract;

import zjm.com.xiangmu.data.bean.Cart_Bean;
import zjm.com.xiangmu.data.bean.Sync_Bean;

/*
* 商品详情
* 商品评论
* 查询购物车
* 同步购物车
* */
public interface Contract_Detail {
    //V层接口
    public interface Detail_View_Interface{

        public void showData(String message);//商品详情

        public void showData_Comment(String message);//商品评论

        public void showData_Cart(Cart_Bean cart_bean);//查询购物车

        public void showData_Sync(Sync_Bean sync_bean);//同步购物车
    }

    //P层接口
    public interface Detail_Presenter_Interface<Detail_View_Interface>{
        //绑定
        public void attahView(Detail_View_Interface detail_view_interface);

        //解绑
        public void deathView(Detail_View_Interface detail_view_interface);

        public void requestData(int commodityId);//商品详情

        public void requestData_Comment(int commodityId, int page, int count); //商品评论

        public void requestData_Cart(int userId, String sessionId); //查询购物车

        public void requestData_Sync(int userId, String sessionId, String data); //同步购物车
    }

    //M层接口
    public interface Detail_Model_Interface{
        //商品详情
        public void getJson(int commodityId, CallBack_Detail callBack_detail);

        //商品评论
        public void getJson_Comment(int commodityId, int page, int count, CallBack_Comment callBack_comment);

        //查询购物车
        public void getJson_Cart(int userId, String sessionId, CallBack_Cart callBack_cart);

        //同步购物车
        public void getJson_Sync(int userId, String sessionId, String data, CallBack_Sync callBack_sync);

        //接口回调--商品详情
        public interface CallBack_Detail{
            //将结果返回给P层
            public void responseData(String message);
        }

        //接口回调--商品评论
        public interface CallBack_Comment{
            //将结果返回给P层
            public void responseData(String message);
        }

        //接口回调--查询购物车
        public interface CallBack_Cart{
            //将结果返回给P层
            public void responseData(Cart_Bean cart_bean);
        }

        //接口回调--同步购物车
        public interface CallBack_Sync{
            //将结果返回给P层
            public void responseData(Sync_Bean sync_bean);
        }
    }
}
