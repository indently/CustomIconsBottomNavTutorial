package com.federicocotogno.custombottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import com.federicocotogno.custombottomnavyt.fragments.FavouritesFragment
import com.federicocotogno.custombottomnavyt.fragments.HomeFragment
import com.federicocotogno.custombottomnavyt.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favouritesFragment = FavouritesFragment()
        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment)

        Handler().postDelayed({
            badgeSetup(R.id.nav_settings,7)
        },2000)

        badgeSetup(R.id.nav_favourites,20000)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG, "Home Selected")
                    badgeClear(R.id.nav_home)
                }
                R.id.nav_favourites -> {
                    setCurrentFragment(favouritesFragment)
                    Log.i(TAG, "Favourites Selected")
                    badgeClear(R.id.nav_favourites)
                }
                R.id.nav_settings -> {
                    setCurrentFragment(settingsFragment)
                    Log.i(TAG, "Settings Selected")
                    badgeClear(R.id.nav_settings)
                }
            }
            true
        }

    }

    private fun badgeSetup(id: Int, alerts: Int) {
        val badge = bottom_navigation.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = alerts
    }

    private fun badgeClear(id: Int) {
        val badgeDrawable = bottom_navigation.getBadge(id)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}