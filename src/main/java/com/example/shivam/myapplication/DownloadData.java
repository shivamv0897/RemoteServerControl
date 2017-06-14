package com.example.shivam.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hp1 on 22-05-2017.
 */

public class DownloadData extends AsyncTask<String,Void,Void> {
    ArrayList<ServerDetails> details=new ArrayList<>();
    String s="";
    String c[];
    public DataDilvery delegate;
    Context context;
    ProgressDialog progressDialog;

    public DownloadData(Context context)
    {
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Fetching Process Data...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            System.out.println("in background    "+"fetching data   ");
            URL url = new URL(params[0]);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            InputStream in=new BufferedInputStream(connection.getInputStream());
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String Line=br.readLine();
            Log.i("data fetched",Line);
            while(Line!=null)
            {  c=Line.split("_");
                for(int i=0;i<c.length;i++)
                {   String f[]=c[i].split(":");
                    ServerDetails d=new ServerDetails();
                    if(f.length>=2){
                        d.setName(f[0]);
                        s=s+f[0];
                        d.setPid(f[1]);
                        s=s+f[1];
                        d.setSmem(f[2]);
                        s=s+f[2];}
                    details.add(d);}
                Line=br.readLine();
            }
        }
        catch (Exception e)
        {
            s=s+e.toString();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("condition:   ","i am in post execute");
        if(progressDialog.isShowing())progressDialog.dismiss();
        delegate.processFinish(details);
    }
}
