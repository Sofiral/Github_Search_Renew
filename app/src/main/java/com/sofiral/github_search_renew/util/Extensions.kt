package com.sofiral.github_search_renew.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import timber.log.Timber
import toothpick.Scope
import toothpick.config.Module

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("TimberExceptionLogging")
fun Any.log(throwable: Throwable, message: String = "") {
    Timber.tag(this.javaClass.simpleName).w(throwable, message)
}

fun Any.log(message: String) {
    Timber.tag(this.javaClass.simpleName).d(message)
}

fun Scope.module(func: Module.() -> Unit): Scope {
    installModules(createModule { func(this) })
    return this
}

fun createModule(func: (Module.() -> (Unit))) = object : Module() {
    init {
        func()
    }
}