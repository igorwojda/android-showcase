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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.base.common.res.Dimen

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = query,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(Dimen.spaceM),
        onValueChange = onQueryChange,
        placeholder = {
            Text(stringResource(R.string.album_list_search_placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )
        },
        trailingIcon =
            if (query.isNotEmpty()) {
                {
                    IconButton(
                        onClick = { onQueryChange("") },
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
    )
}

@Preview
@Composable
private fun SearchBarEmptyPreview() {
    SearchBar(
        query = "",
        onQueryChange = { },
    )
}
