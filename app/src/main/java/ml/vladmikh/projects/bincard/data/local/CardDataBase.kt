package ml.vladmikh.projects.bincard.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ml.vladmikh.projects.bincard.data.local.dao.CardInfoDao
import ml.vladmikh.projects.bincard.data.local.entities.CardInfoLocalDataSource

@Database(entities = [CardInfoLocalDataSource::class], version = 1, exportSchema = false)
abstract class CardDataBase: RoomDatabase() {

    abstract fun cardInfoDao(): CardInfoDao
}