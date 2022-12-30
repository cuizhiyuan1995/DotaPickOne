package ai.pipi.dotapickone.room.Herovs

import androidx.room.*

@Dao
interface HerovsDao {
    @Query("SELECT * FROM herovs")
    fun getAll(): List<Herovs>

    @Query("SELECT * FROM herovs WHERE stratzid IN (:stratzIds)")
    fun loadAllByIds(stratzIds: IntArray): List<Herovs>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg herovss: Herovs?)

    @Delete
    fun delete(herovs: Herovs)
}