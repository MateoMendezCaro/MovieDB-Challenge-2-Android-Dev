package com.app.moviedb.details.model

sealed class DetailUIState {
    data object Loading : DetailUIState()
    data class Success(val detail: DetailUI) : DetailUIState()
    data object Error : DetailUIState()
}