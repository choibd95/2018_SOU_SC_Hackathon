package com.kimjinhyeok.tree.dreamtreeproject;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Tacademy on 2016-10-25.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    // 데이터 수신용

    @Override public void onMessageReceived(RemoteMessage message)
    { String from = message.getFrom(); Map<String, String> data = message.getData();
        String title = data.get("data.key1"); String msg = data.get("data.key2"); } }