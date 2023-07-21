package com.igorwojda.showcase.base.presentation.ext

import android.content.Context
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext

fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebouncer = ActionDebouncer(action)

    // This is the only place in the project where we should actually use setOnClickListener
    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

private class ActionDebouncer(private val action: () -> Unit) {

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }

    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }
}

var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else INVISIBLE
}

fun View.show() {
    visibility = VISIBLE
}

@OptIn(FlowPreview::class)
fun EditText.initSearchBehaviour(
    scope: LifecycleCoroutineScope,
    minimumTextCount: Int,
    delayMs: Long,
    onQueryTextChanged: SearchView.OnQueryTextListener,
) {
    scope.launchWhenResumed {
        callbackFlow {
            val watcher = this@initSearchBehaviour.addTextChangedListener {
                val query = it?.toString() ?: return@addTextChangedListener
                if (query.count() >= minimumTextCount) {
                    this.trySend(query)
                } else {
                    this.trySend(null)
                }
            }
            awaitClose {
                this@initSearchBehaviour.removeTextChangedListener(watcher)
            }
        }.distinctUntilChanged().debounce(delayMs).collectLatest {
            withContext(scope.coroutineContext) {
                onQueryTextChanged.onQueryTextChange(it)
            }
        }
    }
    this.setOnEditorActionListener { _, keyCode, keyEvent ->
        return@setOnEditorActionListener if (keyCode == EditorInfo.IME_ACTION_SEARCH || keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
            onQueryTextChanged.onQueryTextSubmit(this.text?.toString())
            true
        } else {
            false
        }
    }
}

fun View.showKeyboard(): Boolean {
    this.requestFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.showSoftInput(this, 0) ?: false
}

fun View.hideKeyboard(): Boolean {
    this.clearFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0) ?: false
}
