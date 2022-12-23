package abdullah.mola.geogebra.data.remote

import abdullah.mola.geogebra.data.local.Hits
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("applets?filter=author:geogebrateam")
    fun getAllGeoGebra(): Observable<Hits>

}