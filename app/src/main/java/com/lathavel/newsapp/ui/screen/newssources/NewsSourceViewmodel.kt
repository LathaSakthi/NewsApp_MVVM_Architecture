package com.lathavel.newsapp.ui.screen.newssources

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.model.NewsSource
import com.lathavel.newsapp.domain.usecase.GetNewsSourceUsecase
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

data class NewsSourceUiState(
    val isLoading: Boolean = false,
    val newsSourceList: List<NewsSource> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class NewsSourceViewmodel @Inject constructor(
    val getNewsSourceUsecase: GetNewsSourceUsecase
): ViewModel() {

    private val _uiState = MutableStateFlow(NewsSourceUiState())
    val uiState: StateFlow<NewsSourceUiState> = _uiState.asStateFlow()

    init {
        fetchNewsSources() 
    }

    fun fetchNewsSources(){
        getNewsSourceUsecase().onEach {
            result ->
            when(result){
                is Loading ->{

                    Log.e("fetchNewsSources", "LOADING CALLED")
                    _uiState.value = NewsSourceUiState(
                        isLoading = true,
                        newsSourceList = _uiState.value.newsSourceList, //keep old newsSourceList while loading new
                   )
                }

                is Resource.Success -> {
                    Log.e("fetchNewsSources", "Success CALLED")
                    _uiState.value = NewsSourceUiState(
                        isLoading = false,
                        newsSourceList = result.data ?: emptyList(),
                        error = null
                    )
                }

                is Resource.Error -> {
                    Log.e("fetchNewsSources", "Error CALLED")
                    _uiState.value = NewsSourceUiState(
                        isLoading = false,
                        error = result.message
                    )
                }

                else->{
                    Log.e("fetchNewsSources", "ELSE CALLED" + result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}