package com.garvardinho.moblieuptrainee.view

import android.os.Bundle
import android.view.MenuItem
import com.garvardinho.moblieuptrainee.App
import com.garvardinho.moblieuptrainee.R
import com.garvardinho.moblieuptrainee.databinding.ActivityMainBinding
import com.garvardinho.moblieuptrainee.view.screens.CiceroneScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private lateinit var mainActivityBinding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.main_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        App.instance.appComponent.inject(this)
        if (savedInstanceState == null) {
            router.navigateTo(CiceroneScreens.homeScreen())
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.home) {
//            finish()
//            supportFragmentManager.popBackStack()
//            return true
//        }
        return super.onOptionsItemSelected(item)
    }
}