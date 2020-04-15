package np.com.nawarajbista.myweather.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import np.com.nawarajbista.myweather.R
import np.com.nawarajbista.myweather.viewModel.MainActivityViewModel

class DetailOFSearchLocation : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_o_f_search_location)

        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("lon")



        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getCurrentWeatherData(lat, lon, "e220049a903fb389394cea0dda1cb89e")

        viewModel.currentWeather.observe(this, Observer {
            val degree = '\u00B0'
            tv_location.text = it.name.toString()
            tv_description.text = it.weather[0].description
            tv_temperature.text = it.main.temp.toString().plus(degree)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.search -> {
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.home -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                //showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
