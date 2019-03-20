package zjm.com.xiangmu.data;

public class Constant {
    //登录的接口
    public static final String LOGIN_URL = "http://172.17.8.100/small/user/v1/login";

    //注册的接口
    public static final String REGISTER_URL = "http://172.17.8.100/small/user/v1/register";

    //banner轮播的接口
    public static final String BANNER_URL = "http://172.17.8.100/small/commodity/v1/bannerShow";

    //首页商品的接口
    public static final String SHOP_URL = "http://172.17.8.100/small/commodity/v1/commodityList";

    //商品详情
    public static final String XIANGQING_URL = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById";

    //查询商品
    public static final String QUERY_URL = "http:172.17.8.100/small/commodity/v1/findCommodityByKeyword";

    //商品评论列表
    public static final String COMMENT_URL = "http://172.17.8.100/small/commodity/v1/CommodityCommentList";

    //圈子列表的接口--GET请求
    //http://172.17.8.100/small/circle/v1/findCircleList?userId=1010&sessionId=15320748258726&page=1&count=5
    public static final String QUZNZI_URL = "http://172.17.8.100/small/circle/v1/findCircleList";


}
