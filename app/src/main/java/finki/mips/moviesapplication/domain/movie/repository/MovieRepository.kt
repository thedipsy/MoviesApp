package finki.mips.moviesapplication.domain.movie.repository

import finki.mips.moviesapplication.domain.movie.MockMovieDataSource
import finki.mips.moviesapplication.domain.movie.model.Movie

class MovieRepository(private val mockMovieDataSource: MockMovieDataSource){
    //private val remoteMovieDataSource: RemoteMovieDataSource) {

    // suspend fun getMovies(): List<Movie> = remoteMovieDataSource.getMovies()
    // suspend fun getMovie(id: Int): Movie = remoteMovieDataSource.getMovie(id)
    // suspend fun addMovie(movie: Movie) = remoteMovieDataSource.addMovie(movie)

    fun getMockMovies(): List<Movie> = mockMovieDataSource.getMockMovies()
    fun getMockMovie(id: Int): Movie? = mockMovieDataSource.getMockMovie(id)
    fun addMockMovie(movie: Movie) = mockMovieDataSource.addMockMovie(movie)
}