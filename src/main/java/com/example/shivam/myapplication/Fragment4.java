package com.example.shivam.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment4 extends Fragment implements AdapterView.OnItemClickListener,Updation,DataDilvery {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View myView;
    private String mParam1;
    private String mParam2;
    ArrayList<ServerDetails> details=new ArrayList<ServerDetails>();
    String a[]={"a","b","c","d","e","f","g","h","i","j","k"};
    String b[]={"a","b","c","d","e","f","g","h","i","j","k"};
    String c[]={"a","b","c","d","e","f","g","h","i","j","k"};
    String d[]={"a","b","c","d","e","f","g","h","i","j","k"};
    private OnFragmentInteractionListener mListener;
    ListView lv;
    ServerDetails f;
    public Fragment4() {
        // Required empty public constructor
    }

    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        f=(ServerDetails)getActivity().getIntent().getSerializableExtra("delete");
        if(f!=null)
            Toast.makeText(getActivity(),"data aaya",Toast.LENGTH_SHORT).show();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_fragment4, container, false);
        Toast.makeText(getActivity(),MainActivity.server,Toast.LENGTH_SHORT).show();
        lv = (ListView) myView.findViewById(R.id.list);
        try {
            if (isNetworkConnected()) {
                String url="http://www.shopster.96.lt/Details5.php";
                DownloadData d=new DownloadData(getContext());
                d.delegate=this;
                d.execute(url);
            } else {
                for (int i = 0; i < 10; i++) {
                    ServerDetails s = new ServerDetails(a[i], b[i],c[i]);
                    details.add(s);
                }
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT);
        }
        return myView;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "hua kuch", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataDelete(ServerDetails d) {
        details.remove(d);
        CustomAdaptor adaptor=new CustomAdaptor(myView.getContext(),R.layout.spinner,details);
        adaptor.delegate=this;
        lv.setAdapter(adaptor);
    }

    @Override
    public void processFinish(ArrayList<ServerDetails> output) {
        if(output==null)
        {
            Toast.makeText(getActivity(),"output is null",Toast.LENGTH_LONG).show();
        }
        details=output;
        CustomAdaptor adaptor = new CustomAdaptor(myView.getContext(), R.layout.spinner, output);
        adaptor.delegate = this;
        lv.setAdapter(adaptor);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
