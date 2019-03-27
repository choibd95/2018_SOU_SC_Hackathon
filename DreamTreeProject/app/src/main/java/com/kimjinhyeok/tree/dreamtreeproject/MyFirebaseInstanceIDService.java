package com.kimjinhyeok.tree.dreamtreeproject;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Tacademy on 2016-10-25.
 */



public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService
{




    @Override public void onTokenRefresh()
{
    // 토큰 갱신용

    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    sendRegistrationToServer(refreshedToken);







}
    private void sendRegistrationToServer(String refreshedToken) { }
}