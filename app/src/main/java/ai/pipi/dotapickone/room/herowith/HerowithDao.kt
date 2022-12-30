package ai.pipi.dotapickone.room.herowith

import androidx.room.*

@Dao
interface HerowithDao {
    @Query("SELECT * FROM herowith")
    fun getAll(): List<Herowith>

    @Query("SELECT * FROM herowith WHERE stratzid IN (:stratzIds)")
    fun loadAllByIds(stratzIds: IntArray): List<Herowith>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg herowiths: Herowith?)

    @Delete
    fun delete(herowith: Herowith)
}