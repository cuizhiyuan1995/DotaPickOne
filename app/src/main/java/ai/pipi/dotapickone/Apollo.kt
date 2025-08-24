import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


fun apolloClient(): ApolloClient {
    //val logging = HttpLoggingInterceptor()
    //logging.level = HttpLoggingInterceptor.Level.BODY

    val api_key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTdWJqZWN0IjoiMzMyYmYzMTMtYjk2NS00M2EyLTk4MDYtNjU2MDE1MjJlODJhIiwiU3RlYW1JZCI6IjE2ODI2MjM1MCIsIkFQSVVzZXIiOiJ0cnVlIiwibmJmIjoxNzU2MDcyMjg0LCJleHAiOjE3ODc2MDgyODQsImlhdCI6MTc1NjA3MjI4NCwiaXNzIjoiaHR0cHM6Ly9hcGkuc3RyYXR6LmNvbSJ9.vmZvR2E8CG2ziOAXjTzjz7UEdcm0kIiVyUi2NbIX_yI"

    val sqlNormalizedCacheFactory = SqlNormalizedCacheFactory("apollo.db")

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            // Enable basic HTTP logging to help with debugging.
            this.level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    return ApolloClient.Builder()
        .serverUrl("https://api.stratz.com/graphql?jwt=" + api_key)
        .normalizedCache(sqlNormalizedCacheFactory)
        .okHttpClient(client)
        .build()
}



