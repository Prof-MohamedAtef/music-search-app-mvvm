package mohamed.atef.mondiatask.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mohamed.atef.mondiatask.AppUtils.Companion.clientToken
import mohamed.atef.mondiatask.AppUtils.Companion.searchEnd
import mohamed.atef.mondiatask.models.SearchResults
import mohamed.atef.mondiatask.parser.SearchParser
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchRepository (private val responseParser: SearchParser){

    private lateinit var UsersDesires_JsonSTR: String
    private lateinit var reader: BufferedReader

    fun makeSearchCall(
        jsonBody:String
    ):String?{
        val url = URL(searchEnd)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            setRequestProperty("X-MM-GATEWAY-KEY", "G2269608a-bf41-2dc7-cfea-856957fcab1e")
            setRequestProperty("Authorization", "Bearer"+ clientToken)
            doOutput = true
            outputStream.write(jsonBody.toByteArray())
            try {
                val buffer = StringBuffer()
                reader = BufferedReader(InputStreamReader(inputStream))
                var line: String
                if (reader != null) {
                    while (reader.readLine().also { line = it } != null) {
                        buffer.append(
                            """
                $line
                """.trimIndent()
                        )
                    }
                }
                if (buffer.length == 0) {
                    Log.v("Error:", "error ... ")
                    return null
                }
                UsersDesires_JsonSTR = buffer.toString();
                Log.v("JSON:", "Articles String: " + UsersDesires_JsonSTR);
            }catch (e: IOException){
                Log.e("Error:", "Error here Exactly ", e);
                return null
            } finally {
                if (url != null) {
                    (url as? HttpURLConnection)?.disconnect()
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (e: IOException) {
                        Log.e("Error:", "Error closing stream", e);
                        return null
                    }
                }
            }
            try {
                return UsersDesires_JsonSTR
            }catch (e: JSONException){
                Log.e("Error:", "didn't got Users Desires from getJsonData method", e);
                e.printStackTrace();
                return null
            }
        }
        return UsersDesires_JsonSTR
    }
}
