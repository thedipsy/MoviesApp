package finki.mips.moviesapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import finki.mips.moviesapplication.domain.movie.model.Movie
import finki.mips.moviesapplication.domain.movie.repository.MovieRepository

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList : LiveData<List<Movie>> = _moviesList

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie : LiveData<Movie> = _selectedMovie

    private val _newMovieActors = MutableLiveData<List<String>>(listOf())
    val newMovieActors : MutableLiveData<List<String>> = _newMovieActors

    private val _navigateEvent = MutableLiveData<Boolean>()
    val navigateEvent : LiveData<Boolean> = _navigateEvent

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMockMovies().let { movies ->
                _moviesList.postValue(movies)
            }
        }
    }

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMockMovie(id)?.let { movie ->
                _selectedMovie.postValue(movie)
            }
        }
    }

    fun addMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            if(movie.movieName.isNotEmpty()){
                movieRepository.addMockMovie(movie).let { success ->
                    _navigateEvent.postValue(success)
                }
            } else {
                _navigateEvent.postValue(false)
            }
        }
    }

    fun addActor(actor: String) {
        val actors = _newMovieActors.value?.toMutableList() ?: mutableListOf()
        actors.add(actor)
        _newMovieActors.postValue(actors)
    }

}