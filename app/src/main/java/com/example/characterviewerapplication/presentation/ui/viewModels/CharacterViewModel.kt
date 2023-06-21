package com.example.characterviewerapplication.presentation.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterviewerapplication.data.network.AppModule
import com.example.characterviewerapplication.data.network.model.CharacterModel
import com.example.characterviewerapplication.data.network.model.CharacterResponse
import com.example.characterviewerapplication.domain.utilities.Const.Companion.API_QUERY_PARAM
import com.example.characterviewerapplication.domain.utilities.Const.Companion.API_QUERY_PARAM_FORMAT
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLDecoder

/**
 * Character View model class with one function for API calling getCharactersListValues()
 */
class CharacterViewModel : ViewModel() {
    private var job: Job? = null

    private val _selectedCharacter = MutableLiveData<CharacterModel>()
    val selectedCharacter: LiveData<CharacterModel> get() = _selectedCharacter

    private val _selectedCharacterResponse = MutableLiveData<CharacterResponse>()
    val selectedCharacterResponse: LiveData<CharacterResponse> get() = _selectedCharacterResponse

    private val _searchResults = MutableLiveData<List<CharacterModel>>()
    val searchResults: LiveData<List<CharacterModel>> get() = _searchResults

    val isShowProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    private val characterService = AppModule().theRetrofitInstance()

    private var characterList: List<CharacterModel>? = null

    fun selectCharacter(character: CharacterModel) {
        _selectedCharacter.value = character
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled : ${ throwable.localizedMessage}")
    }

    fun getCharactersListValues() {
        job = viewModelScope.launch(exceptionHandler) {
            isShowProgress.value = true
            val response = characterService.getCharactersList(
                URLDecoder.decode(API_QUERY_PARAM, "UTF-8"),
                API_QUERY_PARAM_FORMAT
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    isShowProgress.value = false
                    characterList = response.body()?.relatedTopics
                    _selectedCharacterResponse.postValue(response.body())
                } else {
                    isShowProgress.value = false
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        isShowProgress.value = false
    }

    fun filterCharacterList(query: String) {
        val filteredList = if (query.isBlank()) {
            characterList
        } else {
            characterList?.filter { character ->
                character.text.contains(query, ignoreCase = true)
            }
        }
        _searchResults.postValue(filteredList!!)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel() // Cancel the job when the ViewModel is cleared
    }
}
