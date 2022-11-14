package com.example.gravity.ui.loading

sealed class LinksState {

    object Initial : LinksState()
    object Loading : LinksState()
    data class Success(val link: String) : LinksState()
    data class Error(val error: String) : LinksState()
}
