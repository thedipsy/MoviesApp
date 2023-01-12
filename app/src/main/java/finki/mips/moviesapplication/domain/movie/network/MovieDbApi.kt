package finki.mips.moviesapplication.domain.movie.network

import finki.mips.moviesapplication.domain.movie.model.Movie
import finki.mips.moviesapplication.domain.movie.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieDbApi {

    @GET("movies")
    suspend fun getMovies() : Response<MovieResponse>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: Int) : Response<Movie>

    @POST("movies")
    fun addMovie(movie: Movie)
}