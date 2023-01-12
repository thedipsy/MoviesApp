package finki.mips.moviesapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import finki.mips.moviesapplication.R
import finki.mips.moviesapplication.adapters.ActorAdapter
import finki.mips.moviesapplication.databinding.FragmentAddNewMovieBinding
import finki.mips.moviesapplication.domain.movie.model.Movie
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNewMovieFragment : Fragment() {

    private var _binding: FragmentAddNewMovieBinding? = null
    private val binding get() = _binding!!

    private var adapter : ActorAdapter? = null
    private var moviesViewModel: MoviesViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddNewMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupViewModel()
        observeLiveData()
        setupViews()
    }

    private fun observeLiveData() {
        moviesViewModel?.navigateEvent?.observe(viewLifecycleOwner) { success ->
            when(success){
                true -> findNavController().navigate(R.id.action_AddNewMovieFragment_to_MovieListFragment)
                false -> Toast.makeText(requireContext(), "Movie not saved. Try Again.", Toast.LENGTH_LONG).show()
            }
        }
        moviesViewModel?.newMovieActors?.observe(viewLifecycleOwner) { actors ->
            adapter?.updateActors(actors)
        }
    }

    private fun setupViews() {
        binding.btnAddActor.setOnClickListener { addActor() }
        binding.btnAddMovie.setOnClickListener { addMovie() }
    }

    private fun setupAdapter() {
        adapter = ActorAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        val viewModelFactory = MoviesViewModelsFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private fun addActor() {
        binding.apply {
            etActor.text.toString().let { actor ->
                if(actor.isNotEmpty()) {
                    moviesViewModel?.addActor(actor)
                }
            }
        }
    }

    private fun addMovie() {
        binding.apply {
            val movie = Movie(
                id = Random.nextInt(150),
                movieName = etMovieName.text.toString(),
                description = etMovieDescription.text.toString(),
                directorName = etMovieDirector.text.toString(),
                actors = moviesViewModel?.newMovieActors?.value ?: emptyList()
            )

            moviesViewModel?.addMovie(movie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}