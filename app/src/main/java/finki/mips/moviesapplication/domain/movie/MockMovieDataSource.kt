package finki.mips.moviesapplication.domain.movie

import finki.mips.moviesapplication.domain.movie.model.Movie

interface MockMovieDataSource {
    fun getMockMovies(): List<Movie>
    fun getMockMovie(id: Int): Movie?
    fun addMockMovie(movie: Movie): Boolean
}