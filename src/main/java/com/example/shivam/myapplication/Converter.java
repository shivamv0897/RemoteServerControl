package com.example.shivam.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hp1 on 08-06-2017.
 */

public class Converter {
    private static Context context;
    static Double f;
    static String uni="";
    public Converter(Context context)
    {
        this.context=context;
    }
    public static String convert(String s)
    {   try{
        s=s.replaceAll(",","");
        f=Double.parseDouble(s);
        int x=0;
        while(f>=1024)
        {
            x++;
            f=f/1024;
        }
        if(x==0)
            uni="B";
        else if(x==1)
            uni="KB";
        else if(x==2)
            uni="MB";
        else if(x==3)
            uni="GB";
        else if(x==4)
            uni="TB";
        f=Math.round( f * 100.0 ) / 100.0;}
    catch (Exception e)
    {
        Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        uni=e.getMessage();
    }
        return Double.toString(f)+" "+uni;
    }
}
