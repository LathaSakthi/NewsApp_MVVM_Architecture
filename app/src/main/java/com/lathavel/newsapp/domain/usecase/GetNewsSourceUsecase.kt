package com.lathavel.newsapp.domain.usecase

import android.util.Log
import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.model.NewsSource
import com.lathavel.newsapp.domain.repository.NewsRepository
import com.lathavel.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsSourceUsecase @Inject constructor(val repository: NewsRepository){

    operator fun invoke(): Flow<Resource<List<NewsSource>>> = flow{

        try{
            Log.e("GetNewsSourceUsecase", "invoke Loading CALLED")
            emit(Resource.Loading())
            Log.e("GetNewsSourceUsecase", "invoke Loading ended")
            Log.e("GetNewsSourceUsecase", "getNewsSources api CALLED")
            val result = repository.getNewsSources()
            Log.e("GetNewsSourceUsecase", "getNewsSources api ended")
            Log.e("GetNewsSourceUsecase", "invoke emit CALLED" + result.data)
            emit(result)
            Log.e("GetNewsSourceUsecase", "invoke emit ended" + result.data)
        }catch (e: Exception){
            Log.e("GetNewsSourceUsecase", "Error  CALLED")
            emit(Resource.Error("Unkown error at usecase ${e.localizedMessage}"))
            Log.e("GetNewsSourceUsecase", "Error  ended")
        }


    }
}