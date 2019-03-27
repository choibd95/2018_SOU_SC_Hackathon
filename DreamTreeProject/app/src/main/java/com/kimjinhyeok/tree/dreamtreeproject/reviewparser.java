package com.kimjinhyeok.tree.dreamtreeproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-10-24.
 */

public class reviewparser
{
    public static ArrayList<reviewvo> gsonparse(String jsonString) {
        ArrayList<reviewvo> list = new ArrayList<reviewvo>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<reviewvo>>(){}.getType());
        return list;
    }
    }