package com.lathavel.newsapp.domain.usecase

import android.util.Log
import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.repository.NewsRepository
import com.lathavel.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopHeadLinesUsecase @Inject constructor(val repository: NewsRepository){

    operator fun invoke(countryCode: String): Flow<Resource<List<Article>>> = flow{

        try{
            Log.e("GetTopHeadLinesUsecase", "invoke Loading CALLED")
            emit(Resource.Loading())
            Log.e("GetTopHeadLinesUsecase", "invoke Loading ended")
            Log.e("GetTopHeadLinesUsecase", "getTopHeadLines api CALLED")
            val result = repository.getTopHeadLines(countryCode)
            Log.e("GetTopHeadLinesUsecase", "getTopHeadLines api ended")
            Log.e("GetTopHeadLinesUsecase", "invoke emit CALLED" + result.data)
            emit(result)
            Log.e("GetTopHeadLinesUsecase", "invoke emit ended" + result.data)
        }catch (e: Exception){
            Log.e("GetTopHeadLinesUsecase", "Error  CALLED")
            emit(Resource.Error("Unkown error at usecase ${e.localizedMessage}"))
            Log.e("GetTopHeadLinesUsecase", "Error  ended")
        }


    }
}