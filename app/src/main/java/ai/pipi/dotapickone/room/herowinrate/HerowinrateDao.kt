package ai.pipi.dotapickone.room.herowinrate

import androidx.room.*

@Dao
interface HerowinrateDao {
    @Query("SELECT * FROM herowinrate")
    fun getAll(): List<Herowinrate>

    @Query("SELECT * FROM herowinrate WHERE stratzid IN (:stratzIds)")
    fun loadAllByIds(stratzIds: IntArray): List<Herowinrate>

    @Query("SELECT winrate FROM herowinrate")
    fun getwinratelist():List<Float>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg herowinrates: Herowinrate?)

    @Delete
    fun delete(herowinrate: Herowinrate)
}