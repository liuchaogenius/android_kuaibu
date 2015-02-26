package com.yibu.kuaibu.net;

import java.io.IOException;

public interface ReqsyListener {
    
  
    public void onGet(String response);
   
    public void onIOExc(IOException e);

    public void onErr(Exception e);
}
