package com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview

import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class GridAutofitLayoutManager : GridLayoutManager {

    companion object {
        // spanCount is set during class initialisation. It will be automatically changed later
        const val INITIAL_SPAN_COUNT = 1
    }

    private val defaultColumnWidth by lazy {
        fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()
        dpToPx(48)
    }

    private var columnWidth = 0
        set(value) {
            if (field == value) return

            field = if (value <= 0) defaultColumnWidth else value

            if (field > 0) {
                columnWidthChanged = true
            }
        }

    private var columnWidthChanged = true
    private var lastWidth = 0
    private var lastHeight = 0

    constructor(context: Context, columnWidth: Int) : super(context, INITIAL_SPAN_COUNT) {
        this.columnWidth = columnWidth
    }

    constructor(
        context: Context,
        columnWidth: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(
        context, INITIAL_SPAN_COUNT, orientation, reverseLayout
    ) {
        this.columnWidth = columnWidth
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        val width = width
        val height = height

        @Suppress("detekt.ComplexCondition")
        if (columnWidth > 0 && width > 0 && height > 0 &&
            (columnWidthChanged || lastWidth != width || lastHeight != height)
        ) {
            val totalSpace: Int = if (orientation == LinearLayoutManager.VERTICAL) {
                width - paddingRight - paddingLeft
            } else {
                height - paddingTop - paddingBottom
            }
            val spanCount = max(1, totalSpace / columnWidth)
            setSpanCount(spanCount)
            columnWidthChanged = false
        }
        lastWidth = width
        lastHeight = height
        super.onLayoutChildren(recycler, state)
    }
}
