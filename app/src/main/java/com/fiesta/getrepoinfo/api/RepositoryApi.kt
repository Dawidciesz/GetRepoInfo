package com.fiesta.getrepoinfo.api



import com.fiesta.getrepoinfo.data.Info
import com.fiesta.getrepoinfo.data.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryApi {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/repos/{path}/commits")
    suspend fun getCommits(@Path(value = "path", encoded = true) path: String): List<Info>

    @GET("/repos/{path}")
    suspend fun getRepo(@Path(value = "path", encoded = true) path: String): Repo
}