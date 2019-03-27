    package com.kimjinhyeok.tree.dreamtreeproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jin on 2017-10-28.
 */



    public class BankDialog extends Dialog {

//        private TextView mTitleView;
//        private TextView mContentView;
         Button btok;
         Button btno;
     Button btdate;
     Button bttime;

    
    TextView incon;
    TextView inmoney;

    TextView dal1;
    TextView dal2;
    TextView dal3;
    TextView dal4;

//        private String mTitle = "어플사용안내";
//        private String mContent = "확인보자";



        private View.OnClickListener btlistener;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // 다이얼로그 외부 화면 흐리게 표현
            WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
            lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            lpWindow.dimAmount = 0.6f;
            getWindow().setAttributes(lpWindow);

            setContentView(R.layout.bank);


            btok = (Button) findViewById(R.id.bt_bank_ok);
            btno = (Button) findViewById(R.id.bt_bank_no);


            btdate = (Button) findViewById(R.id.bt_date);
            bttime = (Button) findViewById(R.id.bt_time);
            // 제목과 내용을 생성자에서 셋팅한다.
//            mTitleView.setText(mTitle);
//            mContentView.setText(mContent);

            // 클릭 이벤트 셋팅




                btok.setOnClickListener(btlistener);
                btno.setOnClickListener(btlistener);

            btdate.setOnClickListener(btlistener);
            bttime.setOnClickListener(btlistener);



            incon = (TextView) findViewById(R.id.etcon);
            inmoney =  (TextView) findViewById(R.id.etmoney);


            dal1 = (TextView) findViewById(R.id.dal1);
            dal2 = (TextView) findViewById(R.id.dal2);
            dal3 = (TextView) findViewById(R.id.dal3);
            dal4 = (TextView) findViewById(R.id.dal4);






        }

        // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다
        //
        // .
        public BankDialog(Context context,
                          View.OnClickListener listener) {

            super(context, android.R.style.Theme_Translucent_NoTitleBar);


            btlistener = listener;




//            this.mTitle = title;
//            this.mContent=content;

        }





        void changedate(String day)
        {
            btdate.setText(day);

        }

    void changtime(String time)
    {

        bttime.setText(time);
    }




}



