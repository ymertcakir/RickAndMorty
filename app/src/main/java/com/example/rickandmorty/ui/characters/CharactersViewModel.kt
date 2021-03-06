package com.example.rickandmorty.ui.characters

import androidx.lifecycle.*
import androidx.paging.*
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.data.CharacterListDataSource
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.utils.Constants.Companion.DEFAULT_MAX_PAGE_SIZE
import com.example.rickandmorty.utils.Constants.Companion.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow



class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val rickAndMortyApi  = RetrofitInstance.rickAndMortyApi;
    private val pagingConfig=PagingConfig(pageSize = DEFAULT_PAGE_SIZE, maxSize = DEFAULT_MAX_PAGE_SIZE)


    fun getPagingListData(): Flow<PagingData<Character>> {
        return Pager (config = pagingConfig,
            pagingSourceFactory = { CharacterListDataSource(rickAndMortyApi) }).flow.cachedIn(viewModelScope)
    }




    open class Factory(private val charactersRepository: CharactersRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharactersViewModel(charactersRepository) as T
        }
    }


}