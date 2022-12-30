package ai.pipi.dotapickone.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HeronameDao {
    @Query("SELECT * FROM heroname")
    fun getAll(): List<Heroname>

    @Query("SELECT * FROM heroname WHERE stratzid IN (:stratzIds)")
    fun loadAllByIds(stratzIds: IntArray): List<Heroname>

    @Query("SELECT * FROM heroname WHERE display_name LIKE :name LIMIT 1")
    fun findByName(name: String): Heroname

    @Query("SELECT * FROM heroname ORDER BY display_name ASC")
    fun getHeroSortByAscName(): List<Heroname>

    @Query("SELECT * FROM heroname ORDER BY display_name DESC")
    fun getHeroSortByDescName(): List<Heroname>

    @Query("SELECT * FROM heroname ORDER BY prediction ASC")
    fun getHeroSortByAscWin(): List<Heroname>

    @Query("SELECT * FROM heroname ORDER BY prediction DESC")
    fun getHeroSortByDescWin(): List<Heroname>

    @Query("SELECT * FROM heroname ORDER BY display_name ASC")
    fun getHeroSortByAscName_live(): LiveData<List<Heroname>>

    @Query("SELECT * FROM heroname ORDER BY display_name DESC")
    fun getHeroSortByDescName_live(): LiveData<List<Heroname>>

    @Query("SELECT * FROM heroname ORDER BY prediction ASC")
    fun getHeroSortByAscWin_live(): LiveData<List<Heroname>>

    @Query("SELECT * FROM heroname ORDER BY prediction DESC")
    fun getHeroSortByDescWin_live(): LiveData<List<Heroname>>

    @Query("SELECT * FROM heroname ORDER BY `index` ASC")
    fun getHeroSortByAscRate_live(): LiveData<List<Heroname>>

    @Query("SELECT * FROM heroname ORDER BY `index` DESC")
    fun getHeroSortByDescRate_live(): LiveData<List<Heroname>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg heronames: Heroname?)

    @Delete
    fun delete(heroname: Heroname)

    @Update
    suspend fun updateHeroname(heroname: Heroname)


}