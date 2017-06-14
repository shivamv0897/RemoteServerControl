package com.example.shivam.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shivam on 30-05-2017.
 */

public class LoadData extends AsyncTask<String,Void,Void>{
    Context context;
    View view;
    TextView tv1,tv2,tv3,tv4;
    String s[];
    ProgressDialog progressDialog;
    public LoadData(Context context,TextView tv1,TextView tv2,TextView tv3,TextView tv4) {
        this.context=context;
        this.tv1=tv1;
        this.tv2=tv2;
        this.tv3=tv3;
        this.tv4=tv4;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Fetching Load Data...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    @Override
    protected Void doInBackground(String... params) {
        URL url= null;
        String Data;
        try {
            url = new URL(params[0]);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            InputStream in=new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            Data=br.readLine();
            s=Data.split(":");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(progressDialog.isShowing())progressDialog.dismiss();
        float per=Float.parseFloat(s[0]);
        float tot=Float.parseFloat(s[1]);
        float free=Float.parseFloat(s[2]);
        float perFree=(free/tot)*100;
        tv1.setText(s[0]+"%");
        tv2.setText(Converter.convert(s[1]));
        tv3.setText(Converter.convert(s[2]));
        if((per>=90) && (per<=100))
            tv1.setTextColor(Color.parseColor("#aa0505"));
        else if((per>=70) && (per<90))
            tv1.setTextColor(Color.parseColor("#e56232"));
        else if((per>=0) && (per<70))
            tv1.setTextColor(Color.parseColor("#2176dd"));
        if((perFree>=90) && (perFree<=100))
            tv3.setTextColor(Color.parseColor("#2176dd"));
        else if((perFree>=70) && (perFree<90))
            tv3.setTextColor(Color.parseColor("#e56232"));
        else if((per>=0) && (per<50))
            tv3.setTextColor(Color.parseColor("#aa0505"));
        String f="";
        int x=0;
        for(int i=0;i<s.length;i++)
        {
            if(s[i].matches("[A-Za-z]")&&s[i+1].matches("[0-9]+")&&s[i+2].matches("[0-9]+"))
            {
                f=f+(s[i]);
                f=f+"\t\t\t"+Converter.convert(s[i+1]);
                f=f+"\t\t\t"+Converter.convert(s[i+2]);
                f=f+"\n";
                x++;
            }

        }
        if(x>0)
        {
            tv4.setText("Drive \t\t\t Total Free \t\t\t Total Capacity"+"\n"+f);
        }
    }
}
