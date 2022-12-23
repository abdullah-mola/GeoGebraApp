package abdullah.mola.geogebra.data

import abdullah.mola.geogebra.data.local.GeoGebraDao
import abdullah.mola.geogebra.data.local.Hits
import abdullah.mola.geogebra.data.local.Hit
import abdullah.mola.geogebra.data.local.getDatabase
import abdullah.mola.geogebra.data.remote.ApiService
import android.content.Context
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val locald: GeoGebraDao,
) {
    fun makeApiCall(): Observable<Hits> {
        return api.getAllGeoGebra().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertDataToRoom(geoGebraList: List<Hit>, context: Context) {
        getDatabase(context)?.GeoGebraDao()?.insertAllGeoGebra(geoGebraList)
    }

    fun getGeoGebra(context: Context): Observable<List<Hit>>? {
        return getDatabase(context)?.GeoGebraDao()?.getAllGeoGebra()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}
