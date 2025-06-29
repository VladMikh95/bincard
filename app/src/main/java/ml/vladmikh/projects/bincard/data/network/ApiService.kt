package ml.vladmikh.projects.bincard.data.network

import ml.vladmikh.projects.bincard.data.network.model.CardInfoRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{bin}")
    suspend fun getCardInfo(@Path(value="bin")bin : Int): CardInfoRemoteDataSource
}