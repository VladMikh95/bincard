package ml.vladmikh.projects.bincard.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ml.vladmikh.projects.bincard.data.local.dao.CardInfoDao
import ml.vladmikh.projects.bincard.data.local.entities.CardInfoLocalDataSource
import ml.vladmikh.projects.bincard.data.local.entities.asExternalModel
import ml.vladmikh.projects.bincard.data.model.CardInfo
import ml.vladmikh.projects.bincard.data.network.ApiService
import ml.vladmikh.projects.bincard.data.network.model.asExternalModel
import javax.inject.Inject

class CardInfoRepository @Inject constructor(
    private val cardInfoDao: CardInfoDao,
    private val apiService: ApiService
) {

    suspend fun getCardInfo(bin: Int): CardInfo {

        val cardInfo = apiService.getCardInfo(bin)
        cardInfoDao.insertCardInfoLocalDataSource(cardInfo.asExternalModel())
        return cardInfo.asExternalModel().asExternalModel()
    }

    suspend fun getAllCard(): Flow<List<CardInfo>> {
        return cardInfoDao.getAllCardInfoLocalDataSource().map { cardInfoLocalDataSource ->
            cardInfoLocalDataSource.map(CardInfoLocalDataSource::asExternalModel)
        }
    }
}