package ml.vladmikh.projects.bincard.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ml.vladmikh.projects.bincard.data.model.CardInfo

@Entity(tableName = "card_info_table")
class CardInfoLocalDataSource (

    @PrimaryKey(autoGenerate = true) val id: Int,
    val bank_city: String, //связь один к одному
    val bank_name: String,
    val bank_phone: String,
    val bank_url: String,
    val brand: String,
    val country_alpha2: String,
    val country_currency: String,
    val country_emoji: String,
    val country_latitude: String,
    val country_longitude: String,
    val country_name: String,
    val country_numeric: String,
    val number_length: Int,
    val number_luhn: Boolean,
    val prepaid: String,
    val scheme: String,
    val type: String,

    )

fun CardInfoLocalDataSource.asExternalModel() = CardInfo(
    id = id,
    bankCity = bank_city,
    bankName = bank_name,
    bankPhone = bank_phone,
    bankUrl = bank_url,
    brand = brand,
    countryAlpha2 = country_alpha2,
    countryCurrency = country_currency,
    countryEmoji = country_emoji,
    countryLatitude = country_latitude,
    countryLongitude = country_longitude,
    countryName = country_name,
    countryNumeric = country_numeric,
    numberLength =number_length,
    numberLuhn = number_luhn,
    prepaid = prepaid,
    scheme = scheme,
    type = type
)