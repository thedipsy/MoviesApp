package finki.mips.moviesapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import finki.mips.moviesapplication.R
import finki.mips.moviesapplication.adapters.MovieAdapter
import finki.mips.moviesapplication.databinding.FragmentMovielistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"
    }

    private var _binding: FragmentMovielistBinding? = null
    private val binding get() = _binding!!

    private var adapter: MovieAdapter? = null
    private var moviesViewModel: MoviesViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovielistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_MovieListFragment_to_AddNewMovieFragment)
        }

        adapter = MovieAdapter { id ->
            val bundle = Bundle().apply { putInt(MOVIE_ID, id) }
            findNavController().navigate(R.id.action_MovieListFragment_to_movieDetailsFragment, bundle)
        }
        binding.recyclerView.adapter = adapter

        val viewModelFactory = MoviesViewModelsFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        observeData()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        moviesViewModel?.moviesList?.observe(viewLifecycleOwner) {
            adapter?.updateMovies(it)
        }
    }

    private fun initData() = moviesViewModel?.getMovies()
}