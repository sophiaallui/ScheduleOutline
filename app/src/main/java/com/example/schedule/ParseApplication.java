package com.example.schedule;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Register your parse model
        //ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MMiaQNKJGkn8l3wuuva2ThpZqPDBvsknOTtphHWd")
                .clientKey("eA6RVuQ489rOxThqO5V1sQN9BaCcArRqVkP8Bbvk")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

