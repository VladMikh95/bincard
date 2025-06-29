package ml.vladmikh.projects.bincard.data.network.model

import ml.vladmikh.projects.bincard.data.local.entities.CardInfoLocalDataSource

data class CardInfoRemoteDataSource(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)

fun CardInfoRemoteDataSource.asExternalModel() = CardInfoLocalDataSource(
    id = 0,
    bank_city = bank.city.orEmpty(),
    bank_name = bank.name.orEmpty(),
    bank_phone = bank.phone.orEmpty(),
    bank_url = bank.url.orEmpty(),
    brand = brand.orEmpty(),
    country_alpha2 = country.alpha2.orEmpty(),
    country_currency = country.currency.orEmpty(),
    country_emoji = country.emoji.orEmpty(),
    country_latitude = country.latitude,
    country_longitude = country.longitude,
    country_name = country.name.orEmpty(),
    country_numeric = country.numeric.orEmpty(),
    number_length =number.length,
    number_luhn = number.luhn,
    prepaid = if(prepaid) {
        "YES"
    } else {
        "NO"
    },
    scheme = scheme.orEmpty(),
    type = type.orEmpty()
)