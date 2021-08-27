package mohamed.atef.mondiatask.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import mohamed.atef.mondiatask.WorkerThread.SearchAsyncTask;
import mohamed.atef.mondiatask.repositories.SearchRepository;

public class SearchViewModel extends AndroidViewModel implements SearchAsyncTask.OnQueryResponseChange {
    SearchRepository searchRepository;
    private String includes="true";
    private String limit="20";
    private final MutableLiveData<String> queryResponseLiveData;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
        new SearchRepository();
        queryResponseLiveData = new MutableLiveData<>();
    }

    public void searchApiCall(String query){
        String jsonBody="{ query: "+query+", includeArtists: "+includes+", limit: "+limit+"}";
        SearchAsyncTask searchAsyncTask=new SearchAsyncTask(this, getApplication().getApplicationContext(), query, jsonBody);
        searchAsyncTask.execute();

    }

    public LiveData<String> getQueryResult(){
        return queryResponseLiveData;
    }

    @Override
    public void OnQueryResponseChanged(String responseChange) {
        queryResponseLiveData.postValue(responseChange);
    }
}