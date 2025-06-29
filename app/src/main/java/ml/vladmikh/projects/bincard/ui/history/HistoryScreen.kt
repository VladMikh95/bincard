package ml.vladmikh.projects.bincard.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ml.vladmikh.projects.bincard.R
import ml.vladmikh.projects.bincard.data.model.CardInfo


@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    if (uiState is HistoryUIState.Loaded) {

        val cards = uiState.cards
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(items = cards) {card->
                CardInfoItem(card)

            }
        }
    }

}


@Composable
fun CardInfoItem(cardInfo: CardInfo) {

    val expanded = remember { mutableStateOf(false)}

    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Column() {
            Row {
                Text(
                    text = stringResource(R.string.scheme),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(96.dp)
                )
                Text(
                    text = cardInfo.scheme,
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.type),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(96.dp)
                )
                Text(
                    text = cardInfo.type,
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.brand),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(96.dp)
                )
                Text(
                    text = cardInfo.brand,
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.prepaid),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(96.dp)
                )
                Text(
                    text = stringResource(R.string.yes)
                )

            }

            IconButton(onClick = { expanded.value = !expanded.value },
            ) {
                if (expanded.value) {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = stringResource(R.string.show_less)
                    )
                } else {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.show_more)
                    )
                }

            }

            if (expanded.value) {
                Text(
                    text = stringResource(R.string.country),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = buildString {
                        append(cardInfo.countryAlpha2)
                        append(" ")
                        append(cardInfo.countryName)
                    }
                )
                Row {
                    Text(
                        text = stringResource(R.string.numeric),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.countryNumeric
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.emoji),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.countryEmoji
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.currency),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.countryCurrency
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.latitude),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.countryLatitude.toString()
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.longitude),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.countryLongitude.toString()
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.bank),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.bankName
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.url),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.bankUrl
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.phone),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.bankPhone
                    )
                }

                Row {
                    Text(
                        text = stringResource(R.string.city),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(96.dp)
                    )
                    Text(
                        text = cardInfo.bankCity
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}