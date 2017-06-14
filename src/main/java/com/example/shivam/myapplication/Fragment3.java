package com.example.shivam.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment3 extends Fragment implements service,SerDelete {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    View myView;
    ListView lv;
    ArrayList<ServiceDetails> details=new ArrayList<ServiceDetails>();
    String a[]={"jk","huhu","uhiu","htfytf","hgygy","guygy","uhiu","hyguyh","hgiuhyu","uhiu","uiuoi","uhiu"};
    String b[]={"jk","huhu","uhiu","htfytf","hgygy","guygy","uhiu","hyguyh","hgiuhyu","uhiu","jhjh","jhjjh"};

    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_fragment3, container, false);
        Toast.makeText(getActivity(),MainActivity.server,Toast.LENGTH_SHORT).show();
        lv = (ListView) myView.findViewById(R.id.list1);
        String url="http://shopster.96.lt/ServiceDetails.php";
        ServiceData d=new ServiceData(getContext());
        d.delegate=this;
        d.execute(url);
        return myView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void serviceD(ArrayList<ServiceDetails> output) {
        if(output==null)
        {
            Toast.makeText(getActivity(),"details is null",Toast.LENGTH_LONG).show();
        }
        this.details=output;
        AAdapter adaptor = new AAdapter(myView.getContext(), R.layout.spinner,output);
        adaptor.delegate = this;
        lv.setAdapter(adaptor);
    }

    @Override
    public void serDelte(ServiceDetails d) {
        details.remove(d);
        AAdapter adaptor=new AAdapter(myView.getContext(),R.layout.s_details,details);
        adaptor.delegate=this;
        lv.setAdapter(adaptor);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
