package com.xsoftware.movieapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.fragments.AllMoviesCategoryFragment
import com.xsoftware.movieapplication.fragments.AllSeriesCategoryFragment
import com.xsoftware.movieapplication.fragments.CategoryMovieFragment
import com.xsoftware.movieapplication.fragments.CategorySeriesFragment
import com.xsoftware.movieapplication.fragments.MainFragment
import com.xsoftware.movieapplication.fragments.MovieCategoryFragment
import com.xsoftware.movieapplication.fragments.MovieDetailFragment
import com.xsoftware.movieapplication.fragments.SeriesCategoryFragment
import com.xsoftware.movieapplication.fragments.SeriesDetailFragment
import com.xsoftware.movieapplication.fragments.SeriesFragment
import com.xsoftware.movieapplication.models.Genre
import com.xsoftware.movieapplication.removedfragments.ActionMoviesFragment
import com.xsoftware.movieapplication.removedfragments.AnimationMoviesFragment
import com.xsoftware.movieapplication.removedfragments.CategoriesFragment
import com.xsoftware.movieapplication.removedfragments.ComedyMoviesFragment
import com.xsoftware.movieapplication.removedfragments.CrimeMoviesFragment
import com.xsoftware.movieapplication.removedfragments.DramaMoviesFragment
import com.xsoftware.movieapplication.removedfragments.FantasyMoviesFragment
import com.xsoftware.movieapplication.removedfragments.FullActionMoviesFragment
import com.xsoftware.movieapplication.removedfragments.HorrorMoviesFragment
import com.xsoftware.movieapplication.removedfragments.RomanceMoviesFragment
import com.xsoftware.movieapplication.removedfragments.ScienceFictionMoviesFragment
import com.xsoftware.movieapplication.removedfragments.TurkishMoviesFragment


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
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            add<MainFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }


    fun showCategoryMovie() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            replace<CategoryMovieFragment>(R.id.fragmentContainerView)
            addToBackStack(null) // Geri butonu ile geri dönüş sağlanabilir
        }
    }

    fun showCategorySeries() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            replace<CategorySeriesFragment>(R.id.fragmentContainerView)
            addToBackStack(null) // Geri butonu ile geri dönüş sağlanabilir
        }
    }

    fun showSeriesCategory(genre: Genre) {
        val bundle = bundleOf("genreId" to genre.id, "genreName" to genre.name)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SeriesCategoryFragment>(R.id.fragmentContainerView, args = bundle)
            addToBackStack(null)
        }
    }

    fun showMoviesCategory(genre: Genre) {
        val bundle = bundleOf("genreId" to genre.id, "genreName" to genre.name)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            replace<MovieCategoryFragment>(R.id.fragmentContainerView, args = bundle)
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


    fun showAllMovieCategory(genreId: Int) {
        val bundle = bundleOf("genreId" to genreId)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<AllMoviesCategoryFragment>(R.id.fragmentContainerView, args = bundle)
            addToBackStack(null)
        }
    }

    fun addMovieDetail(movie: Movie) {
        val bundle = bundleOf("movie" to movie)
        supportFragmentManager.commit {

            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            replace<MovieDetailFragment>(R.id.fragmentContainerView, args = bundle)
            setReorderingAllowed(true)
            addToBackStack("movie_navigation_stack")
        }
    }

    fun showSeriesList() {
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

            replace<SeriesFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

    fun addSeriesDetail(series: Series) {
        val bundle = Bundle().apply {
            putParcelable("series", series)
        }
        supportFragmentManager.commit {
            // Animasyonları tanımla
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

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

    fun showCustomDialog() {
        // AlertDialog.Builder kullanarak dialog oluşturma
        val dialogView = layoutInflater.inflate(R.layout.layout_custom_dialog, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Dialogdaki butonları tanımlama
        val btnYes = dialogView.findViewById<Button>(R.id.btnYes)
        val btnNo = dialogView.findViewById<Button>(R.id.btnNo)
        val tvMessage = dialogView.findViewById<TextView>(R.id.tvMessage)
        tvMessage.text = "Are you sure you want to exit?"

        // Yes butonuna tıklanıldığında
        btnYes.setOnClickListener {
            finish()  // Uygulamadan çık
        }

        // No butonuna tıklanıldığında
        btnNo.setOnClickListener {
            dialog.dismiss()  // Dialogu kapat
        }

        // Dialogu göster
        dialog.show()
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            showCustomDialog()
        } else {
            super.onBackPressed()
        }
    }
}