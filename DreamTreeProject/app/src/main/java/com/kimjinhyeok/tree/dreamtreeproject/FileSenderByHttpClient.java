package com.kimjinhyeok.tree.dreamtreeproject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by Tacademy on 2016-11-10.
 */

public class FileSenderByHttpClient {
        private String url;
        private MultipartEntityBuilder params;
        private static final String DEFAULT_ENCODING = "UTF-8";

        /**
         * @param url 접속할 url
         */
        public FileSenderByHttpClient(String url){
                this.url = url;
                params = MultipartEntityBuilder.create();
        }

        public static void main(String[] args) throws Exception {

                //파일을 수신하는 서버측 URL
                FileSenderByHttpClient http = new FileSenderByHttpClient("http://exam.mvn.boards/zzetc/file/httptest.jsp");

                //전송대상 파일들
                String filePath1 = "C:/egov_dev/testdata/sender/DSC01127.jpg";
                String filePath2 = "C:/egov_dev/testdata/sender/정보보호기사.hwp";

                //문자열과 파일들을 동시 전송
                String rtn = http.addParam("test", "문자열 파라메터 테스트다!")
                        .addParam("upload_file1", new File(filePath1))
                        .addParam("upload_file2", new File(filePath2))
                        .submit();
                System.out.println("result : " + rtn);
        }

        /**
         * Map 객체에 파라미터명과 파라미터값을 설정해서 한번에 전달
         * @param Map객체, 파라메터들은 UTF-8로 인코딩 됨
         * @return
         */
        public FileSenderByHttpClient addParam(Map<String, Object> param){
                return addParam(param, DEFAULT_ENCODING);
        }

        /**
         * Map 객체에 담겨진 파라미터값의 타입에 따라 적절한 addParam()을 호출한다.
         * @param Map객체
         * @param encoding charset
         * @return
         */
        public FileSenderByHttpClient addParam(Map<String, Object> param, String encoding){
                for( Map.Entry<String, Object> e : param.entrySet() ){
                        if (e.getValue() instanceof File) {
                                addParam(e.getKey(), (File)e.getValue(), encoding);
                        }else{
                                addParam(e.getKey(), (String)e.getValue(), encoding);
                        }
                }
                return this;
        }

        /**
         * 문자열 파라미터를 추가한다.
         * @param name 파라미터 명
         * @param value 파라미터 값
         * @return
         */
        public FileSenderByHttpClient addParam(String name, String value){
                return addParam(name, value, DEFAULT_ENCODING);
        }

        public FileSenderByHttpClient addParam(String name, String value, String encoding){
                params.addPart(name, new StringBody(value, ContentType.create("text/plain", encoding)));

                return this;
        }

        /**
         * 업로드할 파일 파라미터를 추가한다.
         * @param name 파라미터 명
         * @param file 파일
         * @return
         */
        public FileSenderByHttpClient addParam(String name, File file){
                return addParam(name, file, DEFAULT_ENCODING);
        }

        public FileSenderByHttpClient addParam(String name, File file, String encoding){
                if( file.exists() ){
                        try{
                                params.addPart( name,
                                        new FileBody(file, ContentType.create("application/octet-stream"),
                                                URLEncoder.encode(file.getName(), encoding)));
                        }catch( Exception ex ){ ex.printStackTrace(); }

                }
                return this;
        }

        /**
         * 타겟 URL로 POST 요청을 보낸다.
         * @return 요청결과
         * @throws Exception
         */
        public String submit() throws Exception{

                CloseableHttpClient http = HttpClients.createDefault();
                StringBuffer result = new StringBuffer();

                try{
                        HttpPost post = new HttpPost(url);
                        post.setEntity(params.build());

                        /*************  타켓 URL로 POST 요청 **************/
                        CloseableHttpResponse response = http.execute(post);
                        try{
                                HttpEntity res = response.getEntity();
                                BufferedReader br = new BufferedReader(new InputStreamReader(res.getContent(), Charset.forName("UTF-8")));

                                String buffer = null;
                                while( (buffer = br.readLine())!=null ){
                                        result.append(buffer).append("\r\n");
                                }
                        }finally{
                                response.close();
                        }
                }finally{
                        http.close();
                }

                return result.toString();
        }
}






