package zjm.com.xiangmu.di.contract;

//fragment1--home页面
public interface Contract_rxxp {
    //V层
    public interface Rxxp_View_Interface{
        //数据刷新--商品
        public void showRxxpData(String message);
        //数据刷新--轮播图
        public void showLunBoData(String message);
    }

    //P层
    public interface Rxxp_Presenter_Interface<Rxxp_View_Interface>{
        //绑定
        public void attahView(Rxxp_View_Interface rxxp_view_interface);

        //解绑
        public void deathView(Rxxp_View_Interface rxxp_view_interface);

        //交给M层--商品
        public void requestRxxpData();
        //交给M层--轮播
        public void requestLunData();
    }

    //M层
    public interface Rxxp_Model_Interface{
        //网络请求数据--商品
        public void getRxxpData(Contract_rxxp.Rxxp_Model_Interface.CallBcak_Rxxp callBcak_rxxp);
        //网络请求数据--轮播
        public void getLunData(Contract_rxxp.Rxxp_Model_Interface.CallBcak_Lun callBcak_lun);

        //接口回调
        public interface CallBcak_Rxxp{
            //返回数据
            public void responseRxxpData(String message);
        }

        public interface CallBcak_Lun{
            //返回数据
            public void responseLunData(String message);
        }
    }
}
