package com.lathavel.newsapp.ui.screen.topheadlines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.usecase.GetTopHeadLinesUsecase
import com.lathavel.newsapp.util.Resource
import com.lathavel.newsapp.util.Resource.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class TopHeadlinesUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class TopHeadlinesViewmodel @Inject constructor(
    val getTopHeadLinesUsecase: GetTopHeadLinesUsecase
): ViewModel() {

    private val _uiState = MutableStateFlow(TopHeadlinesUiState())
    val uiState: StateFlow<TopHeadlinesUiState> = _uiState.asStateFlow()

    init {
        fetchTopHeadlines("us") // Fetch for US by default or get from preferences
    }

    fun fetchTopHeadlines(countryCode: String){
        getTopHeadLinesUsecase(countryCode).onEach {
            result ->
            when(result){
                is Loading ->{

                    Log.e("fetchTopHeadlines", "LOADING CALLED")
                    _uiState.value = TopHeadlinesUiState(
                        isLoading = true,
                        articles = _uiState.value.articles, //keep old articles while loading new
                   )
                }

                is Resource.Success -> {
                    Log.e("fetchTopHeadlines", "Success CALLED")
                    _uiState.value = TopHeadlinesUiState(
                        isLoading = false,
                        articles = result.data ?: emptyList(),
                        error = null
                    )
                }

                is Resource.Error -> {
                    Log.e("fetchTopHeadlines", "Error CALLED")
                    _uiState.value = TopHeadlinesUiState(
                        isLoading = false,
                        error = result.message
                    )
                }

                else->{
                    Log.e("fetchTopHeadlines", "ELSE CALLED" + result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}