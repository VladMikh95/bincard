package ml.vladmikh.projects.bincard.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ml.vladmikh.projects.bincard.data.local.CardDataBase
import ml.vladmikh.projects.bincard.data.local.dao.CardInfoDao
import ml.vladmikh.projects.bincard.data.network.ApiService
import ml.vladmikh.projects.bincard.data.repository.CardInfoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://lookup.binlist.net"


    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesCardInfoDao(database: CardDataBase): CardInfoDao {
        return database.cardInfoDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDataBase {
        return Room.databaseBuilder(
            context,
            CardDataBase::class.java,
            "card_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCardInfoRemoteDataSourceRepository(cardInfoDao: CardInfoDao,apiService: ApiService):
            CardInfoRepository = CardInfoRepository(cardInfoDao, apiService)

}