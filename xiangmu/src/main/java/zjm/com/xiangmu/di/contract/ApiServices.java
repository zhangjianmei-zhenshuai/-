package zjm.com.xiangmu.di.contract;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import zjm.com.xiangmu.data.bean.AddressBean;
import zjm.com.xiangmu.data.bean.QuanziBean;

public interface ApiServices {
    //圈子
    @GET("small/circle/v1/findCircleList")
    Observable<QuanziBean> getQuanZi(@Header( "userId" ) int userId, @Header( "sessionId") String sessionId, @Query( "page" )int page, @Query( "count" )int count);

    //收货地址
    @GET("small/user/verify/v1/receiveAddressList")
    Call<AddressBean> getAddress(@Header( "userId" ) int userId, @Header( "sessionId") String sessionId);
}
