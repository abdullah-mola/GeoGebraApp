package abdullah.mola.geogebra.viewModels

import abdullah.mola.geogebra.data.Repository
import abdullah.mola.geogebra.data.local.Hits
import abdullah.mola.geogebra.data.local.Hit
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

private const val TAG = "GeoGebraViewModel"

@HiltViewModel
class GeoGebraViewModel @Inject constructor(
    private val geoGebraRepository: Repository,
) :
    ViewModel() {
    private var remoteGeoGebraList = MutableLiveData<List<Hit>>()
    private var localGeoGebraList = MutableLiveData<List<Hit>>()

    //get data from remote
    fun getRemoteGeoGebraListObserver(): MutableLiveData<List<Hit>> {
        return remoteGeoGebraList

    }

    //get All data from Room
    fun getLocalGeoGebraItem(): MutableLiveData<List<Hit>> {
        return localGeoGebraList
    }

    //get data  directly from Retrofit Api
    fun getDataFromServer(context: Context) {
        geoGebraRepository.makeApiCall().subscribe(getGeoGebraListObserverRx(context))
    }


    fun getData(context: Context) {
        geoGebraRepository.getGeoGebra(context)?.subscribe(getGeoGebraObserverRx())

    }


    // OBSERVERS
    private fun getGeoGebraListObserverRx(context: Context): Observer<Hits> {
        return object : Observer<Hits> {
            override fun onNext(t: Hits) {
                Log.d("ViewModel", "onNext: $t")
                remoteGeoGebraList.postValue(t.hits)
                geoGebraRepository.insertDataToRoom(t.hits, context)
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("ViewModel", "onSubscribe: subscribed ")
            }

        }
    }

    private fun getGeoGebraObserverRx(): Observer<List<Hit>> {
        return object : Observer<List<Hit>> {

            override fun onNext(t: List<Hit>) {
                Log.d("LocalData", "LocalData $t")
                localGeoGebraList.postValue(t)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {

                Log.d("ViewModel", "onSubscribe: subscribed ")
            }

        }
    }
}