import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


fun apolloClient(): ApolloClient {
    //val logging = HttpLoggingInterceptor()
    //logging.level = HttpLoggingInterceptor.Level.BODY

    val api_key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJodHRwczovL3N0ZWFtY29tbXVuaXR5LmNvbS9vcGVuaWQvaWQvNzY1NjExOTgxMjg1MjgwNzgiLCJ1bmlxdWVfbmFtZSI6InVuYmVhcmFibGVodW5nZXImdGhyaXN0IiwiU3ViamVjdCI6IjMzMmJmMzEzLWI5NjUtNDNhMi05ODA2LTY1NjAxNTIyZTgyYSIsIlN0ZWFtSWQiOiIxNjgyNjIzNTAiLCJuYmYiOjE2NzAxOTg0MTIsImV4cCI6MTcwMTczNDQxMiwiaWF0IjoxNjcwMTk4NDEyLCJpc3MiOiJodHRwczovL2FwaS5zdHJhdHouY29tIn0.LRCSkUwKLuARe5Uq3LXklyCmCSFu_XB6ZCbOUIAse8E"

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



