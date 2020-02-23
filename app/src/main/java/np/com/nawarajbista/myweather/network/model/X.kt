package np.com.nawarajbista.myweather.network.model

data class X(
    val coord: Coord,
    val main: Main,
    val name: String,
    val rain: Any,
    val sys: Sys,
    val weather: List<Weather>
)