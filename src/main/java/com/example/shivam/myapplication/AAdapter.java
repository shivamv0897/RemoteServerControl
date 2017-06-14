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
 * Created by shivam on 26-05-2017.
 */

public class AAdapter extends ArrayAdapter<ServiceDetails> implements View.OnClickListener,DataStatus {
    LayoutInflater inflater;
    ArrayList<ServiceDetails> details=new ArrayList<>() ;
    TextView tv1;
    TextView tv2;
    public SerDelete delegate=null;
    View v;
    int g;
    public AAdapter(Context context,int resource) {
        super(context, resource);
    }

    public AAdapter(Context context,int resource,ArrayList<ServiceDetails> objects) {
        super(context, resource, objects);
        this.details=objects;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        v=convertView;
        g=position;
        if(v==null)
        {
            LayoutInflater inflater;
            inflater=LayoutInflater.from(getContext());
            v=inflater.inflate(R.layout.s_details,null);
        }
        ServiceDetails d= (ServiceDetails) getItem(position);
        if(d!=null)
        {
            tv1=(TextView)v.findViewById(R.id.textView6);
            tv2=(TextView)v.findViewById(R.id.textView8);
            Button b=(Button)v.findViewById(R.id.button2);
            if(tv1!=null)
                tv1.setText(d.getServiceName());
            if(tv2!=null)
                tv2.setText(d.getStatus());
            if(b!=null)
            {
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        try {
//                           /* ServerDetails h=(ServerDetails)getItem(position);
//                            String url="http://localhost:8080/WebApplication2/pid="+h.getPid();
//                            DeleteData k =new DeleteData(v.getContext());
//                            k.delegate=f;
//                            k.execute(url);*/
                              processC(0,position);
//                            Toast.makeText(v.getContext(), "deleting item in catch", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(v.getContext(), "item deleted", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//                            Toast.makeText(v.getContext(), "deleting item in catch", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(v.getContext(), "item deleted", Toast.LENGTH_SHORT).show();
//                        }
                        Toast.makeText(v.getContext(), "deleting item in catch", Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(), "item deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        return v;
    }

    @Override
    public void processC(int x, int y) {
        if(x==0)
        {
            Toast.makeText(v.getContext(),"Stopping service "+getItem(y).getServiceName(),Toast.LENGTH_LONG).show();
            delegate.serDelte(getItem(y));
            Toast.makeText(v.getContext(), "service stopped", Toast.LENGTH_SHORT).show();
        }
        else if(x==1)
        {
            Toast.makeText(v.getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }
}

