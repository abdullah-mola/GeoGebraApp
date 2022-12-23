package abdullah.mola.geogebra.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface GeoGebraDao {
    @Query("SELECT * FROM geogebrateam")
    fun getAllGeoGebra(): Observable<List<Hit>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGeoGebra(GeoGebraList: List<Hit>)
}