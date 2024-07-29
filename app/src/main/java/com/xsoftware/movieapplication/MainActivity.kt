package com.xsoftware.movieapplication

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationView
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.Series
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Görünümleri başlat
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        // Toolbar'ı ayarla
        setSupportActionBar(toolbar)

        // ActionBarDrawerToggle'ı ayarla
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Navigation view item seçimi dinleyicisini ayarla
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_movies -> {
                    showMainList()
                }
                R.id.nav_series -> {
                    showSeriesList()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        if (savedInstanceState == null) {
            showMainList()
        }
    }

    fun showMainList() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

    fun addMovieDetail(movie: Movie) {
        val bundle = bundleOf("movie" to movie)
        supportFragmentManager.commit {
            replace<MovieDetailFragment>(R.id.fragmentContainerView, args = bundle)
            setReorderingAllowed(true)
            addToBackStack("movie_navigation_stack")
        }
    }

    fun showSeriesList() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SeriesFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

    fun addSeriesDetail(series: Series) {
        val bundle = Bundle().apply {
            putParcelable("series", series)
        }
        supportFragmentManager.commit {
            replace<SeriesDetailFragment>(R.id.fragmentContainerView, args = bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
    fun showCategories() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<CategoriesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }


    fun showActionMovies() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<ActionMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            AlertDialog.Builder(this)
                .setTitle("Çıkış")
                .setMessage("Çıkmak istiyor musunuz?")
                .setPositiveButton("Evet") { _, _ -> finish() }
                .setNegativeButton("İptal", null)
                .create()
                .show()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}