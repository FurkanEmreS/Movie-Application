package com.xsoftware.movieapplication.removedfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeButton.setOnClickListener{
            (activity as? MainActivity)?.popBackStack()
        }

        // TextView'lara tıklama olaylarını ekle
        binding.textHorror.setOnClickListener{
            (activity as? MainActivity)?.showHorrorMovies()
        }

        binding.textComedy.setOnClickListener{
            (activity as? MainActivity)?.showComedyMovies()
        }

        binding.textTurkish.setOnClickListener{
            (activity as? MainActivity)?.showTurkishMovies()
        }

        binding.textAction.setOnClickListener{
            (activity as? MainActivity)?.showActionMovies()


        }
        binding.textScienceFiction.setOnClickListener{
            (activity as? MainActivity)?.showScienceFictionMovies()
        }

        binding.textRomance.setOnClickListener{
            (activity as? MainActivity)?.showRomanceMovies()
        }

        binding.textAnimation.setOnClickListener{
            (activity as? MainActivity)?.showAnimationMovies()
        }
        binding.textCrime.setOnClickListener{
            (activity as? MainActivity)?.showCrimeMovies()
        }

        binding.textFantasy.setOnClickListener {
            (activity as? MainActivity)?.showFantasyMovies()
        }

        binding.textDrama.setOnClickListener {
            (activity as? MainActivity)?.showDramaMovies()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}