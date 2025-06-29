package ml.vladmikh.projects.bincard.ui.card

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ml.vladmikh.projects.bincard.R
import ml.vladmikh.projects.bincard.utils.ErrorLoadingCard


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(){
        Column(
            modifier  = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.card_instruction),
                textAlign = TextAlign.Center,
                style = typography.titleMedium
            )

            OutlinedTextField(
                value = viewModel.bin,
                onValueChange = { bin -> viewModel.updateBin(bin)},
                label = { Text(text = "00000000") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            if (uiState is CardUIState.Error) {
                if (uiState.error == ErrorLoadingCard.UNCORRECT_TEXT_LENGTH) {
                    Text(
                        text = stringResource(R.string.card_instruction),
                        color  = Color.Red
                    )
                }
            }

            Button(modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
                onClick = {
                    viewModel.getCardInfo()

                }) {
                Text(stringResource(R.string.lookup))
            }

            if (uiState is CardUIState.Error) {

                if (uiState.error == ErrorLoadingCard.CONNECTION_ERROR) {
                    Text(
                        modifier = Modifier.padding(top = 36.dp),
                        text = stringResource(R.string.check_your_internet_connection),
                        color  = Color.Red
                    )
                }

                if (uiState.error == ErrorLoadingCard.ERROR_UNKNOWN) {
                    Text(
                        modifier = Modifier.padding(top = 36.dp),
                        text = stringResource(R.string.unknown_error_contact_support),
                        color  = Color.Red
                    )
                }
            }

            if (uiState is CardUIState.Loaded) {

                val cardInfo = uiState.cardInfo
                Column(
                    modifier
                        .padding(16.dp)
                        .verticalScroll(ScrollState(0))
                ) {
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

}

@Preview()
@Composable
fun CardScreenPreview() {
    CardScreen()
}