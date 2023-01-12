package finki.mips.moviesapplication.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import finki.mips.moviesapplication.domain.movie.mock.MockMovieDataSourceImpl
import finki.mips.moviesapplication.domain.movie.mock.MockMovieDataSourceProvider
import finki.mips.moviesapplication.domain.movie.repository.MovieRepository

class MoviesViewModelsFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(MovieRepository(MockMovieDataSourceProvider.getMockMovieDataSource()))
                //RetrofitMovieDataSource(MovieDbApiProvider.getMovieDbApi())))
    }
}