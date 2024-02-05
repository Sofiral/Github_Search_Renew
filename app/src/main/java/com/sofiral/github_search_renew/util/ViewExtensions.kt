package com.sofiral.github_search_renew.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

val Fragment.viewLifecycle
    get() = viewLifecycleOwner.lifecycle

fun Lifecycle.subscribeOnDestroy(onDestroy: () -> Unit) {
    addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            onDestroy.invoke()
            removeObserver(this)
        }
    })
}

fun <T : RecyclerView.Adapter<*>?> RecyclerView.setAdapter(adapter: T, lifecycle: Lifecycle) {
    this.adapter = adapter
    lifecycle.subscribeOnDestroy { this.adapter = null }
}

fun <T : RecyclerView.Adapter<*>?> ViewPager2.setAdapter(adapter: T, lifecycle: Lifecycle) {
    this.adapter = adapter
    lifecycle.subscribeOnDestroy { this.adapter = null }
}