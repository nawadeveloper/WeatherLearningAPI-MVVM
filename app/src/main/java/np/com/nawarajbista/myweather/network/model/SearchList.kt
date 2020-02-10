package np.com.nawarajbista.myweather.network.model

data class SearchList(
    val cod: String,
    val count: Int,
    val list: List<X>,
    val message: String
)