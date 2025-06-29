package ml.vladmikh.projects.bincard.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ml.vladmikh.projects.bincard.data.local.entities.CardInfoLocalDataSource

@Dao
interface CardInfoDao {

    @Query("SELECT * from card_info_table")
    fun getAllCardInfoLocalDataSource(): Flow<List<CardInfoLocalDataSource>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardInfoLocalDataSource(cardInfoLocalDataSource: CardInfoLocalDataSource)
}