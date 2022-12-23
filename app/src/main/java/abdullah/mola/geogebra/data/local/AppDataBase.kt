package abdullah.mola.geogebra.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hit::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun GeoGebraDao(): GeoGebraDao
}

private var INSTANCE: AppDataBase? = null
fun getDatabase(context: Context): AppDataBase? {
// initializing room data base if there is no one exist
    synchronized(AppDataBase::class.java) {
        if (INSTANCE == null) {
            synchronized(AppDataBase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    " geoGebraData.db"
                ).allowMainThreadQueries().build()
            }
        }
    }
    return INSTANCE
}