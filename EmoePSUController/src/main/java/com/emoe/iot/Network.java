package com.emoe.iot;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.io.BufferedReader;
import com.alibaba.fastjson.*;
import java.io.InputStreamReader;
public class Network {
    Boolean srvStartFlag;
    ServerSocket srvSocket;
    public Network(int port){
        try {
            srvStartFlag = true;
            srvSocket = new ServerSocket(port);
        } catch (IOException  e){
            e.printStackTrace();
        }
    }
    public void stopService(){srvStartFlag = false;}
    class Listener implements Runnable {
        public void run(){
            Socket clientSocket = null;
            while(srvStartFlag){
                try {
                    clientSocket = srvSocket.accept();
                    InputStream clientInputStream = clientSocket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(clientInputStream);
                    BufferedReader bReader = new BufferedReader(inputStreamReader);
                    String jsonStr = "", buffer = null;
                    while((buffer = bReader.readLine()) != null){
                        jsonStr += buffer;
                    }
                    Backend jsonHandleBackend = new Backend(jsonStr);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public class Backend {
        double Voltage, Current, Power;
        public Backend(String responseString){
            JSONObject responseObject = JSON.parseObject(responseString);
            if (responseObject != null){
                String valTmp = responseObject.getString("Voltage");
                if(valTmp != null){
                    Voltage = Double.valueOf(valTmp);
                } else {
                    Voltage = 0;
                }
                valTmp = responseObject.getString("Current");
                if(valTmp != null){
                    Current = Double.valueOf(valTmp);
                } else {
                    Current = 0;
                }
                Power = Voltage * Current;
            }
        }
        public void getCommandString(){}    // TODO: 在此处添加响应JSON字符串生成
        public Double[] getPsuState(){
            return new Double[]{Voltage, Current, Power};
        }
    }
}
