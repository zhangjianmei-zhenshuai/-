package zjm.com.xiangmu.di.contract;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import zjm.com.xiangmu.data.bean.QuanziBean;

public interface ApiServices {
    @GET("small/circle/v1/findCircleList")
    Call<QuanziBean> getQuanZi(@Header( "userId" ) int userId, @Header( "sessionId") String sessionId, @Query( "page" )int page,@Query( "count" )int count);
}
