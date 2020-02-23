package np.com.nawarajbista.myweather.view

import LocationAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_search.*
import np.com.nawarajbista.myweather.R
import np.com.nawarajbista.myweather.viewModel.SearchActivityViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)



        button_search.setOnClickListener{
            if(ed_search_location.text != null || ed_search_location.text!!.isNotEmpty())
                viewModel.searchLocation(ed_search_location.text.toString(), "e220049a903fb389394cea0dda1cb89e")
        }



        viewModel.showProgress.observe(this, Observer {
            if(it) {
                progressbar_search.visibility = VISIBLE
            }
            else {
                progressbar_search.visibility = GONE
            }
        })


        viewModel.locationList.observe(this, Observer {
            adapter.setLocationList(it.list)
        })


        adapter = LocationAdapter(this)
        rv_search_location.adapter = adapter

//        Log.d("SearchActivity", adapter.itemCount.toString())


    }
}
