package com.example.wakouboy.photo4action;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String json = "{'nodes': [1,2,3,'s4'], 'links': {'l1':[1,'s'],'l2':[1,3]}}";
        JSONObject jobj = new JSONObject(json);
        JSONArray nodes = jobj.getJSONArray("nodes");
        JSONObject links = jobj.getJSONObject("links");
        for(int i =0;i<nodes.length();i++) {
            System.out.println(nodes.get(i));
        }
//        for(int i =0;i<links.length();i++){
//            JSONArray list = links.getJSONArray(i);
//            for(int j=0;j<list.length();j++){
//                System.out.println(list.get(j));
//            }
//
//        }
        // 在server端做匹配
        System.out.println(nodes.length());
        System.out.println(links.length());

    }
}