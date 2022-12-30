package ai.pipi.dotapickone.room.herowinrate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Herowinrate (
    @PrimaryKey val stratzId: Int,
    @ColumnInfo(name = "winrate") val winRate: Float
)