package finki.mips.moviesapplication.domain.movie.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbApiProvider {

    companion object {

        @Volatile
        private var INSTANCE: MovieDbApi? = null

        @JvmStatic
        fun getMovieDbApi(): MovieDbApi {
            return INSTANCE ?: synchronized(this) {
                val instance = createMovieDbApi()
                INSTANCE = instance
                instance
            }
        }

        private fun createMovieDbApi(): MovieDbApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val gsonConverterFactory = GsonConverterFactory.create(gson)

            return Retrofit.Builder()
                .baseUrl("https://wwww.movies.com")
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(MovieDbApi::class.java)
        }
    }
}