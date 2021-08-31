package mohamed.atef.mondiatask.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;

import mohamed.atef.mondiatask.WorkerThread.ClientTokenAsyncTask;
import mohamed.atef.mondiatask.WorkerThread.SearchAsyncTask;
import mohamed.atef.mondiatask.models.ClientTokenModel;
import mohamed.atef.mondiatask.repositories.HttpURLConnectionRepository;

public class SearchViewModel extends AndroidViewModel implements SearchAsyncTask.OnQueryResponseChange,
        ClientTokenAsyncTask.OnTokenReturn {
    HttpURLConnectionRepository httpURLConnectionRepository;
    private String includes="true";
    private String limit="20";
    private final MutableLiveData<String> queryResponseLiveData;
    private final MutableLiveData<String> clientTokenLiveData;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
        new HttpURLConnectionRepository();
        queryResponseLiveData = new MutableLiveData<>();
        clientTokenLiveData= new MutableLiveData<>();
    }

    public void searchApiCall(String query, ClientTokenModel clientTokenModel){
        String jsonBody="{ query: "+query+", includeArtists: "+includes+", limit: "+limit+"}";
        SearchAsyncTask searchAsyncTask=new SearchAsyncTask(this, getApplication().getApplicationContext(), query, jsonBody, clientTokenModel);
        searchAsyncTask.execute();

    }

    public void callClientToken(){
        ClientTokenAsyncTask clientTokenAsyncTask=new ClientTokenAsyncTask(this, getApplication().getApplicationContext());
        clientTokenAsyncTask.execute();
    }

    public LiveData<String> getQueryResult(){
        return queryResponseLiveData;
    }

    public LiveData<String> getClientToken(){
        return  clientTokenLiveData;
    }

    @Override
    public void OnQueryResponseChanged(String responseChange) {
        queryResponseLiveData.postValue(responseChange);
    }

    @Override
    public void OnTokenReturn(String response) {
        clientTokenLiveData.postValue(response);
    }
}