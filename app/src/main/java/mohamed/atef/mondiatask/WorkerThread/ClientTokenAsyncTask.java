package mohamed.atef.mondiatask.WorkerThread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import mohamed.atef.mondiatask.R;
import mohamed.atef.mondiatask.repositories.SearchRepository;

public class ClientTokenAsyncTask extends AsyncTask<Void, Void, String> {

    private final Context mContext;
    private final OnTokenReturn mListener;
    private final SearchRepository searchRepository;

    public ClientTokenAsyncTask(OnTokenReturn onTokenReturn, Context context) {
        this.mContext=context;
        this.mListener=onTokenReturn;
        searchRepository=new SearchRepository();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return searchRepository.returnClientToken(mContext);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mListener!=null){
            if (s==null){
                Toast.makeText(mContext,"not found",Toast.LENGTH_LONG).show();
            }else {
                mListener.OnTokenReturn(s);
            }
        }
    }

    public interface OnTokenReturn{
        void OnTokenReturn(String response);
    }
}