package com.fiesta.getrepoinfo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fiesta.getrepoinfo.api.RepositoryApi
import com.fiesta.getrepoinfo.data.*
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor(
    private val repoDataDao: RepoDataDao
) : ViewModel() {
    val repoData = repoDataDao.getOneRepoData().asLiveData()

}
