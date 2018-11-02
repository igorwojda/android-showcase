package com.igorwojda.lastfm.feature.base.presentation

open class BasePresenter<VIEW> {
    var view: VIEW? = null

    fun takeView(view: VIEW) {
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
