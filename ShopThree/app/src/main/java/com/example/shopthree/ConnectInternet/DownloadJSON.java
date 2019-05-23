package com.example.shopthree.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String, Void, String> {
    String link;
    List<HashMap<String, String>> attrs = new ArrayList<>();
    StringBuilder data;

    public DownloadJSON(String link, List<HashMap<String, String>> attrs) {
        this.link = link;
        this.attrs = attrs;
    }

    public DownloadJSON(String link) {
        this.link = link;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        String datasaukhilay = "";
        try {

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(attrs.size() !=0 )
                datasaukhilay = methodPost(connection);
            else
                datasaukhilay = methodGet(connection);
        }
        catch (Exception ex)
        {
            Log.e("Lỗi file doback", ex.toString());
        }
        return datasaukhilay;
    }

    private String methodGet(HttpURLConnection connection)
    {
        String dulieu = "";
        try
        {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            data = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line!=null)
            {
                data.append(line);
                line = bufferedReader.readLine();
            }
            dulieu = data.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
        catch (Exception ex)
        {
            Log.e("Lỗi file methodget", ex.toString());
        }
        return dulieu;
    }

    private String methodPost(HttpURLConnection connection)
    {
        String dulieu = "";
        String key = "";
        String value = "";
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            Uri.Builder uri = new Uri.Builder();
            for(int i=0;i<attrs.size();i++)
            {
                for(Map.Entry<String, String> values : attrs.get(i).entrySet())
                {
                    key = values.getKey();
                    value = values.getValue();
                }

                uri.appendQueryParameter(key, value);
            }

            String query = uri.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            dulieu = methodGet(connection);
        }
        catch (Exception ex)
        {
            Log.e("Lỗi file methodpost", ex.toString());
        }
        return dulieu;
    }
}
