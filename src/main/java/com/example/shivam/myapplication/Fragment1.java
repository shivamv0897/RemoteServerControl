package com.example.shivam.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Fragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment1() {

    }

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }
    TextView tv1,tv2,tv3,tv4;
    double i=1000,j=675;double k;
    private static final int INTERNET = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_fragment1, container, false);
        tv1=(TextView) myView.findViewById(R.id.textView15);
        tv2=(TextView) myView.findViewById(R.id.textView16);
        tv3=(TextView) myView.findViewById(R.id.textView17);
        tv4=(TextView) myView.findViewById(R.id.textView19);
        Toast.makeText(getActivity(),MainActivity.server,Toast.LENGTH_SHORT).show();
        if(isNetworkConnected()) {
//            LoadData d = new LoadData(getActivity(), tv1, tv2, tv3,tv4);
//            String url = "http://www.shopster.96.lt/Load1.php";
//            d.execute(url);
//            verifyStoragePermissions(getActivity());
//            setattributes();
//            writeExternalStrorage("a.txt", "chrome.exe:8456:Console:2:11,280:K");
        }
        else {
            Toast.makeText(getActivity(),"kindly check your Internet!",Toast.LENGTH_LONG).show();
        }
        return myView;
    }

    private void setattributes() {
        tv1.setText(Double.toString(i));
        tv2.setText(Double.toString(j));
        k=(j/i);
        tv3.setText(Double.toString(k*100));
        float per=Float.parseFloat(Double.toString(i));
        float tot=Float.parseFloat(Double.toString(j));
        float free=Float.parseFloat(Double.toString(k*100));
        float perFree=(free/tot)*100;
        if((perFree>=90) && (perFree<=100))
            tv1.setTextColor(Color.parseColor("#aa0505"));
        else if((perFree>=70) && (perFree<90))
            tv1.setTextColor(Color.parseColor("#e56232"));
        else if((perFree>=0) && (perFree<70))
            tv1.setTextColor(Color.parseColor("#2176dd"));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public static void verifyStoragePermissions(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    INTERNET
            );
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void writeExternalStrorage(String s, String txt) {
        try {
            String state= Environment.getExternalStorageState();
            File file =new File("/sdcard/Name/a.txt");
            if(!file.exists())
            {   boolean su=file.createNewFile();
            }
            Log.i("Tag",file.getAbsolutePath());
            FileOutputStream fout=new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(fout);
            writer.append(txt);
            writer.flush();
            writer.close();}

        catch (Exception e)
        {
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
