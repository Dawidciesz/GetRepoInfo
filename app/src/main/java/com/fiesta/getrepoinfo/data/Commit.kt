package com.fiesta.getrepoinfo.data

data class Commit(
    var message: String,
    var committer: Committer
) {
}