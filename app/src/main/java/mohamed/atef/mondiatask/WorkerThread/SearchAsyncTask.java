package mohamed.atef.mondiatask.WorkerThread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import mohamed.atef.mondiatask.repositories.SearchRepository;

public class SearchAsyncTask extends AsyncTask<Void, Void, String> {
    private final Context mContext;
    private final String Query;
    private final String mJsonBody;
    private final OnQueryResponseChange mListener;
    SearchRepository searchRepository;

    public SearchAsyncTask(OnQueryResponseChange onQueryResponseChange, Context context, String query, String jsonBody){
        this.mContext=context;
        this.Query=query;
        this.mJsonBody=jsonBody;
        searchRepository=new SearchRepository();
        this.mListener=onQueryResponseChange;
    }
    @Override
    protected String doInBackground(Void... voids) {
        return searchRepository.makeSearchCall(mJsonBody);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mListener!=null){
            if (s==null){
                Toast.makeText(mContext,"not found",Toast.LENGTH_LONG).show();
            }else {
                mListener.OnQueryResponseChanged(s);
            }
        }
    }

    public interface OnQueryResponseChange{
        public void OnQueryResponseChanged(String responseChange);
    }
}
