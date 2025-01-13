package ai.pipi.dotapickone.room

import ai.pipi.dotapickone.room.Herovs.Herovs
import ai.pipi.dotapickone.room.Herovs.HerovsDao
import ai.pipi.dotapickone.room.herowinrate.Herowinrate
import ai.pipi.dotapickone.room.herowinrate.HerowinrateDao
import ai.pipi.dotapickone.room.herowith.Herowith
import ai.pipi.dotapickone.room.herowith.HerowithDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Heroname::class, Herowinrate::class, Herowith::class, Herovs::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heronameDao(): HeronameDao
    abstract fun herowinrateDao() : HerowinrateDao
    abstract fun herowithDao() : HerowithDao
    abstract fun herovsDao(): HerovsDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,AppDatabase::class.java, "dota2db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}