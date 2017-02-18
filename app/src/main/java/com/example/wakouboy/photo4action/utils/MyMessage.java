package com.example.wakouboy.photo4action.utils;

/**
 * Created by wakouboy on 2017/2/16.
 */

public class MyMessage {
        public String identifier;
        public String name;
        public Body data;
    public MyMessage(){
        this.identifier="iPhone";
        this.name="Hello";
        this.data = new Body();
    }
    public MyMessage(String identifier, String name, Body data) {
        this.identifier = identifier;
        this.name = name;
        this.data = data;
    }
}

