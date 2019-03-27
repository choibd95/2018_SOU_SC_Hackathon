package com.kimjinhyeok.tree.dreamtreeproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-19.
 */
public  class  http extends AppCompatActivity {
    private static final String TAG = "http";
   static Double lat;
    static Double lon;
    static String lat1;
    static String lon1;
    static int urlp =1;
    static boolean searchflag = false;
    String token;
    StringBuilder stringBuilder = new StringBuilder();
    String jsonString = stringBuilder.toString();
    private static volatile  http instance = new http();
    static String sname;
    static String areaname;
    static String nick;
    static String content;
    MultipartEntityBuilder builder = MultipartEntityBuilder.create() //객체 생성...
            .setCharset(Charset.forName("UTF-8")) //인코딩을 UTF-8로.. 다들 UTF-8쓰시죠?
            .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

    Context context;



    private    http()
    {

    }
    public static synchronized http getInstance(){




        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    ImageView imageView;
     ArrayList<vo> list;
    ArrayList<areavo> arealist;
    ArrayList<rankvo> ranklist;
    ArrayList<rankvo> likelist;
    ArrayList<reviewvo> reviewlist;

    int flag;
    LocationManager manager;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button :
                    doAction1();
                    break;
            }
        }
    };
    Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200 :
                 switch (urlp)
                 {
                     case 1 :

                         list = new parser().parse(msg.obj.toString());

                         if(searchflag)
                         {
                             ThirdFragment.click();
                             searchflag = false;
                         }

                         break;
                     case 2 :
                         list = new parser().parse(msg.obj.toString());

                         break;
                     case 3 :
                         arealist = new Gsonparser().gsonparse(msg.obj.toString());
                         Log.v("bbbbbbbbbb","bb");
                         if(viewFragment.mapplag)
                         {
                             viewFragment.arealist = arealist;
                             viewFragment.showMarkerPoint2();
                             viewFragment.mapplag =false;
                         }else
                         {
                             AreaFragment.click();
                         }


                         break;
                     case 4 :

                         Log.v("token","디비 저장성공");
                        DetailActivity.one();
                         break;

                     case  5 :
                         Log.v("token","좋아요성공");
                         break;

                     case  6 :
                         Log.v("token","좋아요삭제");
                         break;
                     case  7 :
                         arealist = new Gsonparser().gsonparse(msg.obj.toString());
                         if(viewFragment.mapplag)
                         {
                             viewFragment.arealist = arealist;
                             viewFragment.showMarkerPoint2();
                             searchflag = false;
                             viewFragment.mapplag = false;
                         }else
                         {
                             NameSeach.click();
                         }


                         break;

                     case  8 :
                         ranklist = new rankparser().gsonparse(msg.obj.toString());
                         RankFragment.click();
                         break;

                     case  9 :
                         likelist = new rankparser().gsonparse(msg.obj.toString());
                         DetailActivity.good();
                         likelist = null;
                     break;

                     case  10 :
                      Log.v("댓글달기성공","댓글달기성공");
                         break;

                     case  11 :
                         reviewlist = new reviewparser().gsonparse(msg.obj.toString());
                        loadActivity.click();
                         break;

                     case  12 :
                         Log.v("이미지업로드 성공","이미지업로드 성공");
                         break;


                     case  20 :


                         list = new parser().parse(msg.obj.toString());

                         if(searchflag)
                         {
                             ThirdFragment.click();
                             searchflag = false;
                         }

                         break;


                 }

                    switch (flag)
                    {
                        case 0 :

                            viewFragment.list = list;
                            viewFragment.showMarkerPoint();
                            break;
                        case 1 :
                        break;
                    }

                    break;

                case 100 :
                    Toast.makeText(MapActivity.context, "응답코드 에러 :" + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                case 999 :
                    Toast.makeText(MapActivity.context, "인터넷을 켜주시기 바랍니다.", Toast.LENGTH_LONG).show();

                    dialog();


                    break;
            }
        }
    };

    class JobThread extends  Thread{
        public synchronized void run(){
            Message msg = null;
            msg = uiHandler.obtainMessage();
            String url = "http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/aaa";
            HttpClient client = null;
            HttpPost request = null;
            HttpResponse response;
            BufferedReader br = null;

            UrlEncodedFormEntity  entity = null;
            try{
                ArrayList<NameValuePair> list = new ArrayList<>();
                switch (urlp)
                {
                    case 1 :
                       if(lat1 != null)
                       {
                           list.add(new BasicNameValuePair("lat", lat1));
                           list.add(new BasicNameValuePair("lon", lon1));
                       }

                        break;
                    case 2:

                        if(lat1 != null)
                        {
                            list.add(new BasicNameValuePair("lat", lat1));
                            list.add(new BasicNameValuePair("lon", lon1));
                            list.add(new BasicNameValuePair("kategorie", viewFragment.setkategorie));
                        }

                        break;

                    case 3:
                        if(viewFragment.mapplag)
                        {
                            list.add(new BasicNameValuePair("area", areaname));
                        }
                        else
                        {
                            list.add(new BasicNameValuePair("area", AreaFragment.area));
                        }

                        break;
                    case 4:
                        list.add(new BasicNameValuePair("token", FirebaseInstanceId.getInstance().getToken()));
                        break;

                    case 5:
                        list.add(new BasicNameValuePair("token", FirebaseInstanceId.getInstance().getToken()));
                        list.add(new BasicNameValuePair("Midx", viewFragment.Midxs));
                        break;

                    case 6:
                        list.add(new BasicNameValuePair("token", FirebaseInstanceId.getInstance().getToken()));
                        list.add(new BasicNameValuePair("Midx", viewFragment.Midxs));
                        break;

                    case 7:
                        if(viewFragment.mapplag)
                        {
                            list.add(new BasicNameValuePair("name", sname));
                        }else
                        {
                            list.add(new BasicNameValuePair("name", NameSeach.name));
                        }
                        break;

                    case 8:
                        break;

                    case 9:
                        list.add(new BasicNameValuePair("Midx", viewFragment.Midxs));
                        break;

                    case 10:

                        String Token = FirebaseInstanceId.getInstance().getToken();

                        list.add(new BasicNameValuePair("Midx", viewFragment.Midxs));
                        list.add(new BasicNameValuePair("id", Token));
                        list.add(new BasicNameValuePair("nickname", nick));
                        list.add(new BasicNameValuePair("text", content));
                        break;

                    case 11:
                        list.add(new BasicNameValuePair("Midx", viewFragment.Midxs));

                        break;

                    case 12:

//                        File file = new File(" test.jpg" );
//                        ContentBody fileBody = new FileBody( file , "image/jpeg" );
//                        MultipartEntity mpEntity = new MultipartEntity(); // HttpMultipartMode
//                        mpEntity.addPart( "내파일", fileBody );
//                        mpEntity.addPart( "type" , new StringBody("어쩌구") );
//                        post.setEntity( mpEntity );
                        break;


                    case  20:

                        if(lat1 != null)
                        {
                            list.add(new BasicNameValuePair("lat", lat1));
                            list.add(new BasicNameValuePair("lon", lon1));
                        }

                        break;

                }


                entity = new UrlEncodedFormEntity(list,"UTF-8");
                client = new DefaultHttpClient();
              //  request = new HttpGet(url);
             switch (urlp)
             {
                 case 1 :
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/map");
                     break;
                 case 2:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/mapkategorie");
                     break;
                 case 3:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/area");
                     break;
                 case 4:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/token");
                     break;
                 case 5:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/likejoin");
                     break;

                 case 6:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/likedel");
                     break;

                 case 7:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/namesearch");
                     break;
                 case 8:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/rank");
                     break;

                 case 9:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/check");
                     break;

                 case 10:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/upload");
                     break;

                 case 11:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/review");
                     break;

                 case 12:
                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/upload");
                     break;

                 case 20:

                     request = new HttpPost("http://dreamtree-env.ezqrtiyq54.ap-northeast-2.elasticbeanstalk.com/ranking");
                     break;


             }
                request.setHeader("User-Agent", "Android");
                request.setEntity(entity);
                response = client.execute(request);

                int code = response.getStatusLine().getStatusCode();
                String str = "";
                StringBuilder stringBuilder = new StringBuilder();

                switch(code){
                    case 200 :
                        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8")); // euc-kr
                        while( (str = br.readLine()) != null ){
                            stringBuilder.append(str).append("\n");
                        }
                        msg.what = 200;
                        msg.obj = stringBuilder;
                        break;
                    default:
                        msg.what = 100;
                        msg.arg1 = code;
                        break;
                }
            }catch(IOException e){
                Log.v("e", String.valueOf(e));


                msg.obj = e;

                msg.what = 999;



            }finally {

            }

               uiHandler.sendMessage(msg);



        }
    }
    synchronized void doAction1()
    {
        flag = 0;


        new JobThread().start();
    }

    synchronized void doAction2()
    {
        flag = 1;


        new JobThread().start();
    }
    ProgressDialog progressDialog = null;
    class JobTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(http.this, "","작업중...");
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.cancel();
        }

        @Override
        protected String doInBackground(String... strings) {
            String strURL = strings[0];
            URL url = null;
            HttpURLConnection connection = null;
            String msg = "";
            BufferedReader br = null;
            try{
                url = new URL(strURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("User-Agent", "android");
                connection.setRequestProperty("id", "abcd");
                connection.setConnectTimeout(10000);

                int code = connection.getResponseCode();
                String str = "";
                StringBuilder stringBuilder = new StringBuilder();
                switch(code){
                    case HttpURLConnection.HTTP_OK : //200
                        br = new BufferedReader(new InputStreamReader
                                (connection.getInputStream(),"utf-8"));
                        while( (str = br.readLine()) != null){
                            stringBuilder.append(str).append("\n");
                        }
                        msg = stringBuilder.toString();
                        break;
                    default:
                        msg = "code :" + code;
                        break;
                }
            }catch(MalformedURLException e){

            }catch(IOException e){
                msg = "오류 :" + e;
            }finally {

            }
            return msg;
        }
    }

    class a extends DefaultHandler {


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
//            title ch, item
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(handler);


        context = http.this;

        Log.v("찍혀라","제발");


    }

    void dialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MapActivity.context);

// 제목셋팅
        alertDialogBuilder.setTitle("인터넷 꺼져있음");



        alertDialogBuilder
                .setMessage("어플을 사용하기위해 인터넷과 GPS를 켜주세요")
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();



    }





}
