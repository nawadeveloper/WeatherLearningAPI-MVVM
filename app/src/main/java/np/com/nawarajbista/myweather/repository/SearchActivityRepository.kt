package np.com.nawarajbista.myweather.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import np.com.nawarajbista.myweather.network.BASE_URL
import np.com.nawarajbista.myweather.network.WeatherNetwork
import np.com.nawarajbista.myweather.network.model.SearchList
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application) {

    var showProgress = MutableLiveData<Boolean>()
    var locationList = MutableLiveData<SearchList>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)

    }


    fun searchLocation(searchKeyWord: String, appID: String) {

        showProgress.value = true

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(WeatherNetwork::class.java)


        service.getLocation(searchKeyWord, appID).enqueue(object : Callback<SearchList> {
            override fun onFailure(call: Call<SearchList>, t: Throwable) {

                showProgress.value = false
                Toast.makeText(application, "Error occur on receiving data", Toast.LENGTH_SHORT).show()
                Log.d("SearchActivity", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<SearchList>,
                response: Response<SearchList>
            ) {

//                Log.d("SearchActivity", "Response: ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                showProgress.value = false
            }

        })

    }
}