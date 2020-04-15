package np.com.nawarajbista.myweather.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import np.com.nawarajbista.myweather.network.BASE_URL
import np.com.nawarajbista.myweather.network.WeatherNetwork
import np.com.nawarajbista.myweather.network.model.X
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepository(val application: Application) {

    val currentWeather = MutableLiveData<X>()

    fun geCurrentWeatherData(lat: String, lon: String, appID: String) {


        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(WeatherNetwork::class.java)


        service.getCurrentWeatherData(lat, lon, appID).enqueue(object : Callback<X> {
            override fun onFailure(call: Call<X>, t: Throwable) {

                Toast.makeText(application, "Error occur on receiving data", Toast.LENGTH_SHORT).show()
                Log.d("SearchActivity", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<X>,
                response: Response<X>
            ) {

                Log.d("SearchActivity", "Response: ${Gson().toJson(response.body())}")
                currentWeather.value = response.body()
            }

        })

    }
}