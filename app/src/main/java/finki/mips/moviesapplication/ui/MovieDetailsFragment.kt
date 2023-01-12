package finki.mips.moviesapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import finki.mips.moviesapplication.R
import finki.mips.moviesapplication.adapters.ActorAdapter
import finki.mips.moviesapplication.databinding.FragmentMovieDetailsBinding
import finki.mips.moviesapplication.domain.movie.model.Movie

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    companion object {
        private const val MOVIE_ID = "movie_id"
    }

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private var adapter : ActorAdapter? = null
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupViewModel()
        observeData()
        initData()
    }

    private fun setupAdapter() {
        adapter = ActorAdapter()
        binding.actorsRecyclerList.adapter = adapter
    }

    private fun setupViewModel() {
        val viewModelFactory = MoviesViewModelsFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private fun observeData() {
        moviesViewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
            updateUi(selectedMovie)
        }
    }

    private fun initData() {
        val selectedMovieId = arguments?.getInt(MOVIE_ID)
        selectedMovieId?.let {
            moviesViewModel.getMovie(selectedMovieId)
        }
    }

    private fun updateUi(movie: Movie?) {
        binding.apply {
            movie?.let { movie ->
                tvMovieId.visibility = View.VISIBLE
                tvMovieName.visibility = View.VISIBLE
                tvMovieDirector.visibility = View.VISIBLE
                tvMovieDescription.visibility = View.VISIBLE
                tvError.visibility = View.GONE

                tvMovieId.text = movie.id.toString()
                tvMovieName.text = movie.movieName
                tvMovieDirector.text = movie.directorName
                tvMovieDescription.text = movie.description

                adapter?.updateActors(movie.actors)
            } ?: run {
                tvMovieId.visibility = View.GONE
                tvMovieName.visibility = View.GONE
                tvMovieDirector.visibility = View.GONE
                tvMovieDescription.visibility = View.GONE
                tvError.visibility = View.VISIBLE
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}