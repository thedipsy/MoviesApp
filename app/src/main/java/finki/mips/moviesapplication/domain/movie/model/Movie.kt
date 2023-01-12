package finki.mips.moviesapplication.domain.movie.model


data class Movie (
    val id: Int,
    val movieName: String,
    val description: String,
    val directorName: String,
    val actors: List<String>
    )