package com.example.shivam.myapplication;

import java.util.ArrayList;

/**
 * Created by hp1 on 29-05-2017.
 */

public interface DataDilvery {
    void processFinish(ArrayList<ServerDetails> output);
}

interface DataStatus{
    void processC(int x, int y);
}

interface service{
    void serviceD(ArrayList<ServiceDetails> details);
}

interface SerDelete{
    void serDelte(ServiceDetails details);
}