package mohamed.atef.mondiatask.repositories;

import android.util.Log;
import android.webkit.HttpAuthHandler;

import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import mohamed.atef.mondiatask.AppUtils;

public class SearchRepository {
    InputStream inputStream = null;
    private BufferedReader reader;

    public String makeSearchCall(String jsonBody) {
        String UsersDesires_JsonSTR = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(AppUtils.Companion.getSearchEnd());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("X-MM-GATEWAY-KEY", "G2269608a-bf41-2dc7-cfea-856957fcab1e");
            urlConnection.setRequestProperty("Authorization", "Bearer Cf8dc6551-bce8-4ce3-a7f6-b362608167d2");

            OutputStreamWriter streamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
            streamWriter.write(jsonBody);
            streamWriter.flush();

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("test", urlConnection.getResponseMessage());
                return null;
            } else {
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    UsersDesires_JsonSTR = null;
                } else {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                }
                String line;
                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                }
                if (buffer.length() == 0) {
                    return null;
                }
                UsersDesires_JsonSTR = buffer.toString();
                Log.v("error:", "Articles String: " + UsersDesires_JsonSTR);
                return null;
            }
        } catch (IOException e) {
            Log.e("error:", "Error here Exactly ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("error:", "Error closing stream", e);
                }
            }
        }
    }
}