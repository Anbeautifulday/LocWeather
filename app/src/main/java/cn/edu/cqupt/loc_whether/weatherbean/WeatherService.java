package cn.edu.cqupt.loc_whether.weatherbean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wentai on 17-8-15.
 */

public interface WeatherService {

    @GET("open/api/weather/json.shtml")
    Call<Weather> getWeather(@Query("city") String city);

}
