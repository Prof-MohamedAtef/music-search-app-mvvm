package mohamed.atef.mondiatask.repositories;

import android.content.Context;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import mohamed.atef.mondiatask.R;
import mohamed.atef.mondiatask.models.ClientTokenModel;
import mohamed.atef.mondiatask.utils.AppUtils;
import mohamed.atef.mondiatask.utils.TrustAllSSSocketFactory;

public class DefaultHttpClientRepository {
    private static final BasicHttpParams sHttpParams = new BasicHttpParams();
    private static final SchemeRegistry sSupportedSchemes = new SchemeRegistry();
    private static final int READ_TIMEOUT = 30000;

    private static final int CONNECT_TIMEOUT = 30000;

    public HttpClient getNewHttpClient() {
        try {
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            sHttpParams.setParameter("http.socket.timeout", READ_TIMEOUT);
            sHttpParams.setParameter("http.connection.timeout", CONNECT_TIMEOUT);

            sSupportedSchemes.register(new Scheme("http",
                    PlainSocketFactory.getSocketFactory(), 80));
            sSupportedSchemes.register(new Scheme("https",
                    TrustAllSSSocketFactory.getSocketFactory(), 443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(
                    params, sSupportedSchemes);
            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public String makeSearchCall(String jsonBody, Context mContext, ClientTokenModel clientTokenModel) {
        HttpClient httpClient=getNewHttpClient();
        try{
            HttpPost httpPost=new HttpPost((
                    AppUtils.Companion.getSearchEnd()));
            String token=clientTokenModel.getTokenType()+" "+clientTokenModel.getAccessToken();
            httpPost.addHeader("Accept","application/json");
            httpPost.addHeader("X-MM-GATEWAY-KEY", mContext.getString(R.string.gateway_key));
            httpPost.addHeader("Authorization", token);


            StringEntity requestEntity = new StringEntity(
                    jsonBody);
            requestEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            httpPost.setEntity(requestEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("httpResponse");
            InputStream inputStream = httpResponse.getEntity()
                    .getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String bufferedStrChunk = null;
            while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                stringBuilder.append(bufferedStrChunk);
            }
            System.out.println("Returning value of doInBackground :"
                    + stringBuilder.toString());
            return stringBuilder.toString();
        } catch (ClientProtocolException cpe) {
            System.out
                    .println("Exception generates caz of httpResponse :"
                            + cpe);
            cpe.printStackTrace();
        } catch (IOException ioe) {
            System.out
                    .println("Second exception generates caz of httpResponse :"
                            + ioe);
            ioe.printStackTrace();
        }
        return null;
    }
}
