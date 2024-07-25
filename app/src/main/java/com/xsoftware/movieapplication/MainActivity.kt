package com.xsoftware.movieapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.xsoftware.movieapplication.models.Movie

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showMainList()
        }
    }

    private fun showMainList() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragmentContainerView)
            addToBackStack("movie_navigation_stack")
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
