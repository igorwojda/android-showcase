package com.igorwojda.showcase.feature.album.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.base.common.res.Dimen
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val minimumProductQuerySize = 1
    val delayBeforeSubmittingQuery = 300L

    var textFieldValue by remember(query) { mutableStateOf(TextFieldValue(query)) }

    // Debounce search - only trigger search after user stops typing
    LaunchedEffect(textFieldValue.text) {
        if (textFieldValue.text.length >= minimumProductQuerySize) {
            delay(delayBeforeSubmittingQuery)
            onSearch(textFieldValue.text)
            onQueryChange(textFieldValue.text)
        } else if (textFieldValue.text.isEmpty()) {
            // Immediately search when query is cleared
            onSearch("")
            onQueryChange("")
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(Dimen.spaceM),
        onValueChange = { newValue ->
            textFieldValue = newValue
        },
        placeholder = {
            Text(stringResource(R.string.search_albums))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )
        },
        trailingIcon =
            if (textFieldValue.text.isNotEmpty()) {
                {
                    IconButton(
                        onClick = {
                            textFieldValue = TextFieldValue("")
                            onSearch("")
                            onQueryChange("")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search",
                        )
                    }
                }
            } else {
                null
            },
        singleLine = true,
        colors =
            OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
            ),
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        query = "Sample query",
        onQueryChange = { },
        onSearch = { },
    )
}

@Preview
@Composable
private fun SearchBarEmptyPreview() {
    SearchBar(
        query = "",
        onQueryChange = { },
        onSearch = { },
    )
}
