package np.com.nawarajbista.myweather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import np.com.nawarajbista.myweather.network.model.X
import np.com.nawarajbista.myweather.repository.MainActivityRepository

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainActivityRepository(application)
    val currentWeather: LiveData<X>

    init {
        this.currentWeather = repository.currentWeather
    }

    fun getCurrentWeatherData(lat: String, lon: String, appID: String) {
        repository.geCurrentWeatherData(lat, lon, appID)
    }
}