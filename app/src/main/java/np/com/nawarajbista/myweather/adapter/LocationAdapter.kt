import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.location_name_adatper.view.*
import np.com.nawarajbista.myweather.view.DetailOFSearchLocation
import np.com.nawarajbista.myweather.R
import np.com.nawarajbista.myweather.network.model.X

class LocationAdapter(private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private var list: List<X> = ArrayList()

    fun setLocationList(list: List<X>){
        this.list = list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.latLng.text = list[position].coord.lat.toString()
        holder.log.text = list[position].coord.lon.toString()

        holder.rootView.setOnClickListener {
            Log.d("startActivity", list[position].coord.lat.toString())
            val intent = Intent(context, DetailOFSearchLocation::class.java)
            intent.putExtra("name", list[position].name)
            intent.putExtra("lat", list[position].coord.lat.toString())
            intent.putExtra("lon", list[position].coord.lon.toString())
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.location_name_adatper,
                parent,
                false
            )
        )
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.tv_location_name!!
        val latLng = v.tv_lat_lng!!
        val log = v.tv_log_lng!!
        val rootView = v.child_root!!

    }

}