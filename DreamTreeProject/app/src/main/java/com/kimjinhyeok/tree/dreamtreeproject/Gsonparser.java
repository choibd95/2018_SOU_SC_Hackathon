package com.kimjinhyeok.tree.dreamtreeproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-10-24.
 */

public class Gsonparser
{
    public static ArrayList<areavo> gsonparse(String jsonString) {
        ArrayList<areavo> list = new ArrayList<areavo>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<areavo>>(){}.getType());
        return list;
    }
    }