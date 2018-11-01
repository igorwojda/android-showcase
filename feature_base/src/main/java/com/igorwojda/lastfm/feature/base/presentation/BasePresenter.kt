package com.igorwojda.lastfm.feature.base.presentation

open class BasePresenter<V> {
    var view: V? = null

    fun takeView(view: V) {
        this.view = view
        onTakeView()
    }

    fun dropView() {
        view = null
        onDropView()
    }

    protected open fun onTakeView() {}

    protected open fun onDropView() {}
}
