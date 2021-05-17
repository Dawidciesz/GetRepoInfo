package com.fiesta.getrepoinfo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fiesta.getrepoinfo.api.RepositoryApi
import com.fiesta.getrepoinfo.data.*
import kotlinx.coroutines.launch

class FetchViewModel @ViewModelInject constructor(
    private val context: Context,
    private val repoDataDao: RepoDataDao,
    private val api: RepositoryApi
) : ViewModel() {
    private val commitsLiveData = MutableLiveData<List<Info>>()
    val commits: LiveData<List<Info>> = commitsLiveData
    val repoData = repoDataDao.getOneRepoData().asLiveData()

    private val repoLiveData = MutableLiveData<Repo>()
    val repo: LiveData<Repo> = repoLiveData

    fun onGetButtonClick(nameRepo: String) {
if (!nameRepo.equals("") && isNetworkAvailable(context)) {
    viewModelScope.launch {
        val commits = api.getCommits(nameRepo)
        commitsLiveData.value = commits
        val repo = api.getRepo(nameRepo)
        repoLiveData.value = repo
        var itemList = arrayListOf<RepoData>()
        for (info in commits)
            itemList.add(
                RepoData(
                    info.sha, info.commit.message,
                    repo.id, info.commit.committer.name, nameRepo
                )
            )
        repoDataDao.insertAll(itemList)
    }
} else {
}
    }
    @SuppressLint("ServiceCast")
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
