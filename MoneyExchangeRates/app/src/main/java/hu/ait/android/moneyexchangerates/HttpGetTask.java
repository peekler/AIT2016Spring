package hu.ait.android.moneyexchangerates;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetTask extends AsyncTask<String, Void, String> {

    private Context context;

    public HttpGetTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(params[0]);
            conn = (HttpURLConnection) url.openConnection();
            is = conn.getInputStream();

            StringBuffer sb = new StringBuffer();

            int b;
            while ((b = is.read())!=-1) {
                sb.append((char)b);
            }

            result = sb.toString();
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
             if (is != null) {
                 try {
                     is.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (conn != null) {
                conn.disconnect();
             }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String getResult) {
        ((MainActivity)context).showResult(getResult);
    }
}
