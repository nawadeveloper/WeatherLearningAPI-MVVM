package np.com.nawarajbista.myweather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import np.com.nawarajbista.myweather.network.model.SearchList
import np.com.nawarajbista.myweather.repository.SearchActivityRepository

class SearchActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SearchActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val locationList: LiveData<SearchList>

    init {
        this.showProgress = repository.showProgress
        this.locationList = repository.locationList
    }

    fun changeState() {
        repository.changeState()
    }

    fun searchLocation(searchKeyWord: String, appID: String) {
        repository.searchLocation(searchKeyWord, appID)
    }
}