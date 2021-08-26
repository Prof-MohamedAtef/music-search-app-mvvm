package mohamed.atef.mondiatask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mohamed.atef.mondiatask.AppUtils.Companion.includes
import mohamed.atef.mondiatask.AppUtils.Companion.limit
import mohamed.atef.mondiatask.repositories.SearchRepository

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val queryText: String
) : ViewModel(){
    init {
        searchApiCall(queryText)
    }
    private val queryResponseLiveData= MutableLiveData<String>()

    fun searchApiCall(query:String){
        viewModelScope.launch(Dispatchers.IO){
            val jsonBody="{ query:\"$query\", includeArtists: \"$includes\", limit: \"$limit\"}"
            val result= searchRepository.makeSearchCall(jsonBody)
            queryResponseLiveData.postValue(result)
        }
    }

    fun getQueryResponse(): LiveData<String> {
        return queryResponseLiveData
    }
}