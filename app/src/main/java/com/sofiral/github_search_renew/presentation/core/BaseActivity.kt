package com.sofiral.github_search_renew.presentation.core

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.sofiral.github_search_renew.util.log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpDelegate
import toothpick.configuration.MultipleRootException
import toothpick.ktp.delegate.inject
import toothpick.locators.NoFactoryFoundException

abstract class BaseActivity : AppCompatActivity(), Presentable {

    private val navigatorHolder by inject<NavigatorHolder>()
    abstract val navigator: Navigator

    private val mvpDelegate: MvpDelegate<out BaseActivity> by lazy { MvpDelegate(this) }
    protected val scopeName: String = this.javaClass.name
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tryInject(savedInstanceState)
    }

    private fun tryInject(savedInstanceState: Bundle?) {
        try {
            injectDependencies()
            mvpDelegate.onCreate(savedInstanceState)
        } catch (e: MultipleRootException) {
            dispatchInjectException(e)
        } catch (t: NoFactoryFoundException) {
            dispatchInjectException(t)
        }
    }

    protected open fun injectDependencies() {}

    private fun dispatchInjectException(t: Exception) {
        log(t, "Something goes wrong during injection")
    }

    override fun onResume() {
        super.onResume()
        mvpDelegate.onAttach()
    }

    override fun onStart() {
        super.onStart()
        mvpDelegate.onAttach()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDelegate.onSaveInstanceState(outState)
        mvpDelegate.onDetach()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        mvpDelegate.onDetach()
    }

    @Suppress("TooGenericExceptionCaught")
    override fun onDestroy() {
        super.onDestroy()
        try {
            mvpDelegate.onDestroyView()
            if (isFinishing) {
                mvpDelegate.onDestroy()
                compositeDisposable.dispose()
            }
        } catch (e: Exception) {
            log(e, message = "Problem with destroy view in Moxy")
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}