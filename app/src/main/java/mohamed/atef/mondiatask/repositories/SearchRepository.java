package mohamed.atef.mondiatask.repositories;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import mohamed.atef.mondiatask.AppUtils;
import mohamed.atef.mondiatask.R;
import mohamed.atef.mondiatask.models.ClientTokenModel;

public class SearchRepository {
    InputStream inputStream = null;
    private BufferedReader reader;

    public String makeSearchCall(String jsonBody, Context mContext, ClientTokenModel clientTokenModel) {
        String UsersDesires_JsonSTR = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(AppUtils.Companion.getSearchEnd());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.addRequestProperty("Authorization", clientTokenModel.getTokenType() + " " + clientTokenModel.getAccessToken());
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.addRequestProperty("X-MM-GATEWAY-KEY", mContext.getString(R.string.gateway_key));

            try {
                OutputStreamWriter streamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                streamWriter.write(jsonBody);
                streamWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("test", urlConnection.getResponseMessage());
                return null;
            } else {
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    UsersDesires_JsonSTR = null;
                }
                try{
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;
                    if (reader != null) {
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line + "\n");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
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


    public String returnClientToken(Context mContext) {
        String UsersDesires_JsonSTR = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(AppUtils.Companion.getClientTokenEnd());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.addRequestProperty("X-MM-GATEWAY-KEY", mContext.getString(R.string.gateway_key));
            urlConnection.connect();

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("test", urlConnection.getResponseMessage());
                return null;
            } else {
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
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
                return UsersDesires_JsonSTR;
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