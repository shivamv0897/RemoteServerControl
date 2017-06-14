package com.example.shivam.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    TextView tv1,tv2,tv3,tv4;
    private OnFragmentInteractionListener mListener;

    public Fragment2() {
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    List<ServerDetails> details=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_fragment2, container, false);
        tv1=(TextView)myView.findViewById(R.id.textView7);
        tv2=(TextView)myView.findViewById(R.id.textView14);
        tv3=(TextView)myView.findViewById(R.id.textView20);
        tv4=(TextView)myView.findViewById(R.id.textView22);

        Toast.makeText(getActivity(),MainActivity.server,Toast.LENGTH_SHORT).show();
       if(isNetworkConnected()) {
            Toast.makeText(getActivity(),"network connected",Toast.LENGTH_SHORT);
            String url="http://www.shopster.96.lt/server2.php";
            ServerData d= new ServerData(tv1,tv2,tv3,tv4,getContext());
            d.execute(url);

        }
        else{
           Toast.makeText(getActivity(),"kindly check your internet connection!",Toast.LENGTH_SHORT);}
        return myView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
