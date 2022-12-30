package ai.pipi.dotapickone.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Heroname(
    @PrimaryKey val stratzId: Int,
    @ColumnInfo(name = "display_name") val displayName: String?,
    @ColumnInfo(name = "prediction") var predicted_winrate: Float,
    @ColumnInfo(name = "index") var withvs_index: Float
)
