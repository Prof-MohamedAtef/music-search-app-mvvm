package mohamed.atef.mondiatask.data.remote

import android.content.Context
import android.os.AsyncTask

class SearchAsyncTask(applicationContext: Context) : AsyncTask<String,Void,String>(){
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String {
        TODO("Not yet implemented")
    }
}