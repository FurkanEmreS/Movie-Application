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
import com.xsoftware.movieapplication.models.Genre


class MainActivity : AppCompatActivity() {

    var shouldReloadFullActionMovies = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showMainList()
        }
    }

    fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    fun showMainList() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

    fun showCategorySeries() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<CategorySeriesFragment>(R.id.fragmentContainerView)
            addToBackStack(null) // Geri butonu ile geri dönüş sağlanabilir
        }
    }

    fun showSeriesCategory(genre: Genre) {
        val bundle = bundleOf("genreId" to genre.id)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SeriesCategoryFragment>(R.id.fragmentContainerView, args = bundle)
            addToBackStack(null)
        }
    }


    fun showAllSeriesCategory(genreId: Int) {
        val bundle = bundleOf("genreId" to genreId)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<AllSeriesCategoryFragment>(R.id.fragmentContainerView, args = bundle)
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
        supportFragmentManager.commit {
            replace<CategoriesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    fun showActionMovies() {
        supportFragmentManager.commit {
            replace<ActionMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
        }
    }

    fun showFullActionMovies() {
        shouldReloadFullActionMovies = true
        supportFragmentManager.commit {
            replace<FullActionMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    fun showComedyMovies() {
       // supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<ComedyMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
           // addToBackStack(null)
        }
    }

    fun showHorrorMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<HorrorMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
          //  addToBackStack(null)
        }
    }

    fun showTurkishMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<TurkishMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }

    fun showScienceFictionMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<ScienceFictionMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }

    fun showRomanceMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<RomanceMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }
    fun showAnimationMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<AnimationMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }

    fun showCrimeMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<CrimeMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }
    fun showFantasyMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<FantasyMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }
    fun showDramaMovies() {
        //supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            replace<DramaMoviesFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            //  addToBackStack(null)
        }
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            AlertDialog.Builder(this)
                .setTitle("Çıkış")
                .setMessage("Çıkmak istiyor musunuz?")
                .setPositiveButton("Evet") { _, _ -> finish() }
                .setNegativeButton("İptal", null)
                .create()
                .show()
        } else {
            super.onBackPressed()
        }
    }
}