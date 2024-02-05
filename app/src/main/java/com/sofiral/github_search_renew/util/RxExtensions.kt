package com.sofiral.github_search_renew.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T : Any> Observable<T>.async(): Observable<T> = subscribeOn(
    Schedulers.io()
).observeOn(
    AndroidSchedulers.mainThread()
)

fun <T : Any> Flowable<T>.async(): Flowable<T> = subscribeOn(
    Schedulers.io()
).observeOn(
    AndroidSchedulers.mainThread()
)

fun <T : Any> Single<T>.async(): Single<T> = subscribeOn(
    Schedulers.io()
).observeOn(
    AndroidSchedulers.mainThread()
)

fun <T> Maybe<T>.async(): Maybe<T> = subscribeOn(
    Schedulers.io()
).observeOn(
    AndroidSchedulers.mainThread()
)

fun Completable.async(): Completable = subscribeOn(
    Schedulers.io()
).observeOn(
    AndroidSchedulers.mainThread()
)