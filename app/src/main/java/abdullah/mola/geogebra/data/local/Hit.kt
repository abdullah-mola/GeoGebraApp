package abdullah.mola.geogebra.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "geogebrateam")
data class Hit(
    @PrimaryKey
    val id: String,
    val title: String?,
    val dateCreated: String,
    val dateModified: String,
    val thumbUrl: String,
    val type: String,
    val visibility: String,
    @Embedded(prefix = "creator_")
    val creator: Creator
)




