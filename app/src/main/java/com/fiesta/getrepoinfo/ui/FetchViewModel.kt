package com.fiesta.getrepoinfo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiesta.getrepoinfo.api.RepositoryApi
import com.fiesta.getrepoinfo.data.Info
import com.fiesta.getrepoinfo.data.Repo
import kotlinx.coroutines.launch

    class FetchViewModel @ViewModelInject constructor(
      private val  api : RepositoryApi
    ) : ViewModel() {
        private val commitsLiveData = MutableLiveData<List<Info>>()
        val commits : LiveData<List<Info>> = commitsLiveData

        private val repoLiveData = MutableLiveData<Repo>()
        val repo: LiveData<Repo> = repoLiveData


//        init {
//            viewModelScope.launch {
//                val commits = api.getCommits()
//                commitsLiveData.value = commits
//
//            }
//        }
        fun onGetButtonClick(nameRepo: String) {
            viewModelScope.launch {
                val commits = api.getCommits(nameRepo)
                commitsLiveData.value = commits

                val repo = api.getRepo(nameRepo)
                repoLiveData.value = repo
            }
//            }
        }
    }
