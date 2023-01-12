package finki.mips.moviesapplication.domain.movie

import finki.mips.moviesapplication.domain.movie.model.Movie

interface RemoteMovieDataSource {

    suspend fun getMovies() : List<Movie>
    suspend fun getMovie(id: Int) : Movie
    suspend fun addMovie(movie: Movie)

}