package finki.mips.moviesapplication.domain.movie.network

import finki.mips.moviesapplication.domain.movie.RemoteMovieDataSource
import finki.mips.moviesapplication.domain.movie.model.Movie
import java.lang.Exception

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi) : RemoteMovieDataSource {

    override suspend fun getMovies(): List<Movie> {
        val movieResponse = movieDbApi.getMovies()
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody.results
        }
        throw Exception(movieResponse.message())
    }

    override suspend fun getMovie(id: Int): Movie {
        val movieResponse = movieDbApi.getMovie(id)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody
        }
        throw Exception(movieResponse.message())
    }

    override suspend fun addMovie(movie: Movie) {
        movieDbApi.addMovie(movie)
    }
}