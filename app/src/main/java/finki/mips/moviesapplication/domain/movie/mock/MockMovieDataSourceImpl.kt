package finki.mips.moviesapplication.domain.movie.mock

import finki.mips.moviesapplication.domain.movie.MockMovieDataSource
import finki.mips.moviesapplication.domain.movie.model.Movie

class MockMovieDataSourceImpl : MockMovieDataSource {
    private val _mockedMovies = mutableListOf(
        Movie(
            id = 1,
            movieName = "Hangover 1",
            description = "The gang gets shitfaced and does stupid things.",
            directorName = "Todd Phillips",
            actors = listOf(
                "Zach Galifianakis (Alan)",
                "Bradley Cooper (Phil)",
                "Ed Helms (Stu)"
            )
        ),
        Movie(
            id = 2,
            movieName = "Hangover 2",
            description = "The gang gets shitfaced and does stupid things part 2.",
            directorName = "Todd Phillips",
            actors = listOf(
                "Zach Galifianakis (Alan)",
                "Bradley Cooper (Phil)",
                "Ed Helms (Stu)"
            )
        ),
        Movie(
            id = 3,
            movieName = "Hangover 3",
            description = "The gang gets shitfaced and does stupid things part 3.",
            directorName = "Todd Phillips",
            actors = listOf(
                "Zach Galifianakis (Alan)",
                "Bradley Cooper (Phil)",
                "Ed Helms (Stu)"
            )
        )
    )


    override fun getMockMovies(): List<Movie> = _mockedMovies

    override fun getMockMovie(id: Int): Movie? = _mockedMovies.firstOrNull { movie -> movie.id == id }

    override fun addMockMovie(movie: Movie) : Boolean = _mockedMovies.add(movie)
}