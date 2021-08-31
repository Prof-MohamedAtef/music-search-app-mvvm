package mohamed.atef.mondiatask.WorkerThread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import mohamed.atef.mondiatask.models.ClientTokenModel;
import mohamed.atef.mondiatask.repositories.DefaultHttpClientRepository;
import mohamed.atef.mondiatask.repositories.HttpURLConnectionRepository;

public class SearchAsyncTask extends AsyncTask<Void, Void, String> {
    private final Context mContext;
    private final String Query;
    private final String mJsonBody;
    private final OnQueryResponseChange mListener;
    private final ClientTokenModel clienTokenModel;
    HttpURLConnectionRepository httpURLConnectionRepository;
    DefaultHttpClientRepository defaultHttpClientRepository;

    public SearchAsyncTask(OnQueryResponseChange onQueryResponseChange, Context context, String query, String jsonBody, ClientTokenModel clientTokenModel){
        this.mContext=context;
        this.Query=query;
        this.mJsonBody=jsonBody;
        this.clienTokenModel=clientTokenModel;
        httpURLConnectionRepository =new HttpURLConnectionRepository();
        defaultHttpClientRepository =new DefaultHttpClientRepository();
        this.mListener=onQueryResponseChange;
    }
    @Override
    protected String doInBackground(Void... voids) {
        /*
        // please note: changing from both of repositories to change the used mechanism in your call
        // 1. HttpURLConnection 2. DefaultHttpClient
         */
//        return httpURLConnectionRepository.makeSearchCall(mJsonBody, mContext, clienTokenModel);
        return defaultHttpClientRepository.makeSearchCall(mJsonBody,mContext,clienTokenModel);
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
        void OnQueryResponseChanged(String responseChange);
    }
}