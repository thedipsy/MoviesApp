package finki.mips.moviesapplication.domain.movie.mock

class MockMovieDataSourceProvider {

    companion object {

        @Volatile
        private var INSTANCE: MockMovieDataSourceImpl? = null

        @JvmStatic
        fun getMockMovieDataSource(): MockMovieDataSourceImpl {
            return INSTANCE ?: synchronized(this) {
                val instance = MockMovieDataSourceImpl()
                INSTANCE = instance
                instance
            }
        }
    }
}