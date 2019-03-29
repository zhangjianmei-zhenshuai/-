package zjm.com.xiangmu.di.contract;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import zjm.com.xiangmu.data.bean.AddressBean;
import zjm.com.xiangmu.data.bean.Order_Bean;
import zjm.com.xiangmu.data.bean.Pay_Bean;
import zjm.com.xiangmu.data.bean.QuanziBean;

public interface ApiServices {
    //圈子列表
    @GET("small/circle/v1/findCircleList")
    Observable<QuanziBean> getQuanZi(@Header( "userId" ) int userId, @Header( "sessionId") String sessionId, @Query( "page" )int page, @Query( "count" )int count);

    //收货地址列表
    @GET("small/user/verify/v1/receiveAddressList")
    Call<AddressBean> getAddress(@Header( "userId" ) int userId, @Header( "sessionId") String sessionId);

    //创建订单
    @POST("small/order/verify/v1/createOrder")
    Observable<Order_Bean> getOrder (@Header( "userId" )int userId,@Header( "sessionId" ) String sessionId,@Query("totalPrice") String totalPrice, @Query("addressId") int addressId, @Query("orderInfo") String orderInfo);

    //支付
    @POST("small/order/verify/v1/pay")
    Observable<Pay_Bean> getPay (@Header( "userId" )int userId, @Header( "sessionId" ) String sessionId, @Query("orderId") String orderId, @Query("payType") int payType);
}
