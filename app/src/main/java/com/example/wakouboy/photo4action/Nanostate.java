package com.example.wakouboy.photo4action;

import android.util.Log;

import com.example.wakouboy.photo4action.utils.Body;
import com.example.wakouboy.photo4action.utils.MyMessage;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;

/**
 * Created by wakouboy on 2017/2/15.
 */
public class Nanostate extends WebSocketClient{
    private Nanostate(URI serverURI) {
        super(serverURI);
    };
    private static volatile Nanostate nanostate = null;
    public static Nanostate getNanostate() throws java.net.URISyntaxException {
        if (nanostate == null) {
            synchronized (Nanostate.class){
                if (nanostate == null){
                    nanostate = new Nanostate(new URI(DataCenter.address));
                }
            }
        }
        return nanostate;
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.v("Open", "Connect to Server");
        MyMessage message = new MyMessage();
        send(message);
    }
    @Override
    public void onMessage(String s) {
        Log.v("MyString", "String");
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        // hub端发送是ByteBuffer形式
        Log.v("Buffer", "Buffer");
        try {
            MessageHandler.messageHandler(getString(bytes));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.v("Close", "Close");
    }
    @Override
    public void onError(Exception e) {
        Log.v("ErrorServer", Log.getStackTraceString(e));
    }
    public void send(MyMessage message) throws NotYetConnectedException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(message);
            super.send(json);
        } catch (Exception e){
            Log.v("Error", Log.getStackTraceString(e));
        }
    }
    private static String getString(ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try
        {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Log.v("BufWrong", "BufWrong");
            return "";
        }
    }
}
/*
发送消息的逻辑：
  1 手机->TDW，   hello
 */