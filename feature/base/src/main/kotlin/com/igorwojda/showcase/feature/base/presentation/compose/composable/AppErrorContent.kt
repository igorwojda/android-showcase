package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.igorwojda.showcase.feature.base.R
import com.igorwojda.showcase.feature.base.domain.error.AppError

/** Retry is an explicit UI decision; callers retain the original operation. */
@Composable
fun AppErrorContent(
    error: AppError,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var dialogVisible by rememberSaveable(error) { mutableStateOf(true) }
    val message = stringResource(error.messageResource())
    val canRetry = error == AppError.Network || error == AppError.Server
    Column(modifier = modifier) {
        Text(message)
        if (canRetry) {
            Button(onClick = onRetry) { Text(stringResource(R.string.error_retry)) }
        }
    }
    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false },
            title = { Text(stringResource(R.string.error_title)) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { dialogVisible = false }) {
                    Text(stringResource(R.string.error_close))
                }
            },
            dismissButton = {
                if (canRetry) {
                    TextButton(onClick = onRetry) { Text(stringResource(R.string.error_retry)) }
                }
            },
        )
    }
}

private fun AppError.messageResource(): Int =
    when (this) {
        AppError.Network -> R.string.error_network
        AppError.Unauthorized -> R.string.error_unauthorized
        AppError.Server -> R.string.error_server
        AppError.Unknown -> R.string.error_unknown
    }
