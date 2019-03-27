package com.kimjinhyeok.tree.dreamtreeproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class BoardWrite extends AppCompatActivity {
    Uri imageUri;
    LayoutInflater inflater = null;

    AQuery aq;
    EditText board_title_input;
    EditText board_content_input;

    final int GALLERY =100;
    final int CAMERA = 200;
    static File imageFile;
    int height;
    int width;
    Bitmap myBitmap;
    LinearLayout image_container;
    SharedPreferences sharedPreferences;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.likeview :
                    camera();
                    break;
                case R.id.bg3:
                    image();
                    break;

                case R.id.go:
                    write();
                    break;
            }
        }
    };

    /** 카메라 메소드**/
    public void camera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA);
    }


    /** 이미지 메소드**/
    public  void image(){
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Select File"), GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;

            final Bitmap bitmap2 = BitmapFactory.decodeFile(imageUri.getPath(),
                    options);
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmap2, 1000, 1000, true);
            try {
                ExifInterface exif = new ExifInterface(imageUri.getPath());
                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                //가로 세로 잡아주는 메솓드
                Bitmap bmRotated = rotateBitmap(bitmap, orientation);
                myBitmap = bmRotated;

                /** uri로 이미지 파일 경로 가져오기 */
                Uri fileUri = getImageUri(BoardWrite.this, bmRotated);
                String myFilePath = getPathFromUri(fileUri);
                imageFile = new File(myFilePath);
                /** */

                /** uri로 이미지 파일명 가져오기 */
                String imageName = getImageNameToUri(fileUri);

                int index = imageName.indexOf(".");
                String imageName2 = imageName.substring(0, index);


                /** */
                ImageView myImageView = new ImageView(BoardWrite.this);
                myImageView.setImageBitmap(bmRotated);
                image_container.addView(myImageView);


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (requestCode == GALLERY && resultCode == RESULT_OK) {
            if (data != null) {

                /** uri로 이미지 파일 경로 가져오기 */
                Uri selectedImageUri = data.getData();
                String myFilePath = getPathFromUri(selectedImageUri);
                imageFile =  new File(myFilePath);
                /***/

                /** uri로 이미지 파일명 가져오기 */
                String imageName = getImageNameToUri(selectedImageUri);
                int index = imageName.indexOf(".");
                String imageName2 = imageName.substring(0, index);

                /***/

                String tempPath = getPath(selectedImageUri, BoardWrite.this);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                btmapOptions.inSampleSize = 8;
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                try {

                    Bitmap bitmap = scaleImage(this, selectedImageUri);

                    ImageView myImageView = new ImageView(BoardWrite.this);
                    myImageView.setImageBitmap(bitmap);
                    image_container.addView(myImageView);


                    myBitmap = bitmap;

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPathFromUri(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
        cursor.close();

        return path;
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     *
     *
     */

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "Sample");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Sample", "Oops! Failed create " + "Sample"
                        + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap scaleImage(Context context, Uri photoUri)
            throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > 240 || rotatedHeight > 240) {
            float widthRatio = ((float) rotatedWidth) / ((float) 240);
            float heightRatio = ((float) rotatedHeight) / ((float) 240);
            float maxRatio = Math.max(widthRatio, heightRatio);

            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

      /*
       * if the orientation is not 0 (or -1, which means we don't know), we
       * have to do a rotation.
       */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0,
                    srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        }

        String type = context.getContentResolver().getType(photoUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (type.equals("image/png")) {
            srcBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        } else if (type.equals("image/jpg") || type.equals("image/jpeg")) {
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        }
        byte[] bMapArray = baos.toByteArray();
        baos.close();
        return BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },
                null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    // URI에서 파일명 가져오기
    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }




    public void write(){
        String url = "http://192.168.205.108:4000/upload";// -> 서버주소 -> 즉 데이터를 보내고 받을 곳
        Map<String, Object> params = new HashMap<String, Object>();// -> 데이터를 담을 그릇

        params.put("file",imageFile);
        params.put("mem_idx",sharedPreferences.getInt("mem_idx",0));
        params.put("board_title",board_title_input.getText());
        params.put("board_content",board_content_input.getText());

        http.urlp = 12;



        aq.ajax(url,params, String.class, new AjaxCallback<String>() {// -> 데이터를 보내고 받는다
            @Override            //->string안으로 데이터가 들어온다
            public void callback(String url, String string, AjaxStatus status) {
                Intent intent  = new Intent(BoardWrite.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.likeview).setOnClickListener(handler);
        findViewById(R.id.bg3).setOnClickListener(handler);
//        image_container = (LinearLayout) findViewById(R.id.image_container);
        findViewById(R.id.go).setOnClickListener(handler);

        board_title_input = (EditText)findViewById(R.id.board_title_input);
        board_content_input = (EditText)findViewById(R.id.board_content_input);
        aq = new AQuery(this);


        sharedPreferences = this.getSharedPreferences("info",Context.MODE_PRIVATE);


        inflater = getLayoutInflater();

    }
}