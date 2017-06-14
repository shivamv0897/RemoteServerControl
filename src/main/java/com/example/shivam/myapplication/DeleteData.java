package com.example.shivam.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hp1 on 29-05-2017.
 */

public class DeleteData extends AsyncTask<String,Void,Integer> {
    public DataStatus delegate=null;
    Context context;
    int x=0,p;
    String Data;
    public DeleteData(Context context,int p) {
        this.context=context;
        this.p=p;
    }

    @Override
    protected Integer doInBackground(String... params) {
        URL url= null;
        try {
            url = new URL(params[0]);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            InputStream in=new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            Data=br.readLine();
            if(Data==null || Data.equalsIgnoreCase("null"))
            {x=1;}
        } catch (Exception e) {
            e.printStackTrace();
            x=2;
        }

        return x;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(context,"value of Data="+Data+" and value of x= "+Integer.toString(integer),Toast.LENGTH_SHORT).show();
        delegate.processC(integer,p);
    }
}
