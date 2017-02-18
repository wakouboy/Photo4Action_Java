package com.example.wakouboy.photo4action.utils;

import java.util.Date;

/**
 * Created by wakouboy on 2017/2/17.
 */

public class Body {
   public String payload;
   public String target;
   public String ts;
    public String error;
    public Body(){
        this.payload="";
        this.target="tdw";
        this.ts = new Date().toString();
        this.error="";
    }
    public Body(String payload, String target, String ts, String error) {
        this.payload = payload;
        this.target = target;
        this.ts = ts;
        this.error = error;
    }
}
