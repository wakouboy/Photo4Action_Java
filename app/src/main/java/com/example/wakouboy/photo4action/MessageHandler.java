package com.example.wakouboy.photo4action;

import android.util.Log;

import com.example.wakouboy.photo4action.utils.Body;
import com.example.wakouboy.photo4action.utils.MyMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.R.attr.data;

/**
 * Created by wakouboy on 2017/2/17.
 */

public class MessageHandler {
    public static void messageHandler(String aMessage) throws org.json.JSONException{
        JSONObject message = new JSONObject(aMessage);
        String identifier = message.getString("identifier");
        String name = message.getString("name");
        JSONObject data = message.getJSONObject("data");
        String target = data.getString("target");
        if (! target.equals("iPhone")){
            Log.v("Wrong target", target + name);
            return;
        }
        switch (identifier) {
            case "tdw":
                switch (name){
                    case "Bye":
                        Log.v("Bye", "From tdw");
                        break;
                    case "GraphLayout":
                        Log.v("Get", "GraphLayout");
                        DataCenter.GraphLayout = data.getJSONObject("payload");
                        JSONArray nodes = DataCenter.GraphLayout.getJSONArray("nodes");
                        JSONObject links = DataCenter.GraphLayout.getJSONObject("links");
                        Log.v("GraphSize", nodes.length()+" "+links.length());
                        break;
                    default:
                        Log.v("Wrong", "Wrong Message");
                }
                break;
            default:
                Log.v("Wrong", "Wrong Source");
        }
    }
}
