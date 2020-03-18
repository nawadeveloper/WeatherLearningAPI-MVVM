package np.com.nawarajbista.myweather.network


import np.com.nawarajbista.myweather.network.model.SearchList
import np.com.nawarajbista.myweather.network.model.X
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "http://api.openweathermap.org/"

interface WeatherNetwork {

    @GET("data/2.5/find?")
    fun getLocation(@Query("q")searchString: String, @Query("appid")appID: String) : Call<SearchList>



    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String, @Query("units") units: String = "metric"): Call<X>

}