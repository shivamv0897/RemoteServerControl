package com.example.shivam.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hp1 on 25-05-2017.
 */

public class CustomAdaptor extends ArrayAdapter<ServerDetails> implements View.OnClickListener,DataStatus{
    LayoutInflater inflater;
    ArrayList<ServerDetails> details=new ArrayList<>() ;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    Updation delegate=null;
    DataStatus f=this;
    View v;
    int g;
    Context context;
    public CustomAdaptor(Context context,int resource) {
        super(context, resource);
    }

    public CustomAdaptor( Context context,  int resource,  ArrayList<ServerDetails> objects) {
        super(context, resource, objects);
         details=objects;
        this.context=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        v=convertView;
        g=position;
        if(v==null)
        {
           LayoutInflater inflater;
           inflater=LayoutInflater.from(getContext());
           v=inflater.inflate(R.layout.spinner,null);
        }
        ServerDetails d= (ServerDetails)getItem(position);
        if(d!=null)
        {
            tv1=(TextView)v.findViewById(R.id.textView2);
            tv3=(TextView)v.findViewById(R.id.textView4);
            tv4=(TextView)v.findViewById(R.id.textView5);
            Button b=(Button)v.findViewById(R.id.button);
            if(tv1!=null)
                tv1.setText(d.getName());
            if(tv3!=null)
                tv3.setText(d.getPid());
            if(tv4!=null) {
                try {
                    if (!d.getSmem().equals("") && !d.getSmem().equals(" ") && d.getSmem() != null)
                        tv4.setText(Converter.convert(d.getSmem()));
                    else
                        tv4.setText(d.getSmem());
                }
                catch (Exception e)
                { tv4.setText(d.getSmem());}

            }
            if(b!=null)
            {
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                           /* ServerDetails h=(ServerDetails)getItem(position);
                            String url="http://localhost:8080/WebApplication2/pid="+h.getPid();
                            DeleteData k =new DeleteData(v.getContext());
                            k.delegate=f;
                            k.execute(url);*/
                            f.processC(0,position);
                        } catch (Exception e) {
                            Toast.makeText(v.getContext(), "deleting item in catch", Toast.LENGTH_SHORT).show();
                            delegate.dataDelete(getItem(position));
                            Toast.makeText(v.getContext(), "item deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        return v;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void processC(int x,int y) {
        if(x==0)
        {
            Toast.makeText(v.getContext(),"Stopping process "+getItem(y).getName(),Toast.LENGTH_LONG).show();
            delegate.dataDelete(getItem(y));
            Toast.makeText(v.getContext(), "process stopped", Toast.LENGTH_SHORT).show();
        }
        else if(x==1)
        {
            Toast.makeText(v.getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
