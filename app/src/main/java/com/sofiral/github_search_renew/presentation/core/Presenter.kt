package com.sofiral.github_search_renew.presentation.core

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

abstract class Presenter<T : Presentable> : MvpPresenter<T>() {

    private val fullLifeCycleCompositeDisposable = CompositeDisposable()

    protected fun Disposable.addFullLifeCycle(): Disposable {
        fullLifeCycleCompositeDisposable.add(this)
        return this
    }

    protected open fun dispose() {
        fullLifeCycleCompositeDisposable.dispose()
    }

    override fun onDestroy() {
        dispose()
    }
}