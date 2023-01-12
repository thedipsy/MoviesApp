package finki.mips.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import finki.mips.moviesapplication.R
import finki.mips.moviesapplication.domain.movie.model.Movie

class MovieAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    val onClickListener: (id: Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickListener(item.id) }
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvMovieId: TextView = view.findViewById(R.id.tvMovieId)
        private var tvMovieName: TextView = view.findViewById(R.id.tvMovieName)
        private var tvMovieDirector: TextView = view.findViewById(R.id.tvMovieDirector)

        fun bind(movie: Movie) {
            tvMovieId.text = movie.id.toString()
            tvMovieName.text = movie.movieName
            tvMovieDirector.text = movie.directorName
        }

    }

}