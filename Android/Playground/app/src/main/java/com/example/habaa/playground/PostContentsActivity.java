package com.example.habaa.playground;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PostContentsActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String imageFilePath,comment;
    private Uri photoUri;
    File pic;
    LocationManager manager;
    GPSListener gpsListener;
    String lat,lon;

    Button btnPostPic,btnStartCamera;
    ImageView imageView;
    PostPicRequest postPicRequest;
    String registerContentsSucess;
    EditText etComment;

    String loginUidx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_contents);
        imageView = findViewById(R.id.imageView);
        btnPostPic = (Button) findViewById(R.id.btnPostPic);
        //btnStartCamera = (Button) findViewById(R.id.btnStartCamera);
        etComment = findViewById(R.id.etComment);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        loginUidx = pref.getString("uidx", LoginActivity.spuidx);
        Log.d("*****LOG*****",loginUidx);
        sendTakePhotoIntent();
        startLocationService();

    }
    //메인페이지
    public void clickMainPage(View v){
        Intent intent = new Intent(PostContentsActivity.this,MainActivity.class);
        startActivity(intent);
    }
    //사진올리기
    public void clickGoPostContent(View v){
        Intent intent = new Intent(PostContentsActivity.this,PostContentsActivity.class);
        startActivity(intent);
    }
    //마이페이지
    public void clickMyPage(View v){
        Intent intent = new Intent(PostContentsActivity.this,MyPageActivity.class);
        startActivity(intent);
    }
    //로그아웃
    public void clickLogOut(View v){

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("uidx");
        editor.remove("pwd");
        editor.remove("name");
        editor.remove("age");
        editor.remove("gender");
        editor.remove("loginChecker");
        editor.commit();

        Intent intent = new Intent(PostContentsActivity.this,StartActivity.class);
        startActivity(intent);
    }


    public void clickStartCamera(View v){
        sendTakePhotoIntent();
    }


    public void clickPostPic(View v){
        comment = etComment.getText().toString();
        System.out.println("PIC -- "+ pic);
        postPicRequest = (PostPicRequest) new PostPicRequest().execute(StartActivity.serverUrl+"/registerContents.do",comment);
    }

    //============================통신 부분=======================
    public class PostPicRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            postPic(strings[0],strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("registerContentsSucess -- "+registerContentsSucess);
            if(registerContentsSucess.equals("false")) {
                Toast.makeText(PostContentsActivity.this,"fail",Toast.LENGTH_LONG).show();
            } else if(registerContentsSucess.equals("true")){
                startLocationService();
                Intent intent = new Intent(PostContentsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }

        JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        public void postPic(String url,String comment){
            try
            {

                jsonDataObject.put("img",pic);
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                /*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("cmt", comment));
                nameValuePairs.add(new BasicNameValuePair("lat", "37"));
                nameValuePairs.add(new BasicNameValuePair("lon", "127"));
                nameValuePairs.add(new BasicNameValuePair("uidx", "1"));*/


                FileBody bin = new FileBody(pic);
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.addPart("img", bin);
                builder.addTextBody("cmt",comment,ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("lat",lat,ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("lon",lon,ContentType.create("Multipart/related", "UTF-8"));
                //Log.d("test**************",loginUidx);
                builder.addTextBody("uidx",loginUidx,ContentType.create("Multipart/related", "UTF-8"));

                //MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                //reqEntity.addPart("img", bin);
                InputStream inputStream = null;
                post.setEntity(builder.build());
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println("Buffered rd -- "+line);
                    JSONArray jsonArray = new JSONArray(line);
                    System.out.println("JSONArray -- "+jsonArray);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String isSuccess = jsonObject.getString("registerContentsSucess");
                        System.out.println(isSuccess+ " - POST - "+isSuccess);
                        registerContentsSucess=isSuccess;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
//==============================base64==================================
   /* public String toStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }*/

    /*public String encoder(File pic) {
        String base64Image = "";
        File file = pic;
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = getByte(file);
            System.out.print("imageData[] -- "+imageData);
            imageInFile.read(imageData);
            base64Image = Base64.encodeToString(imageData,1);
            System.out.println("base64Image -- "+ base64Image);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }
    public byte[] getByte(File unfile) {
        byte[] getBytes = {};
        try {
            File file = unfile;
            getBytes = new byte[(int) file.length()];
            InputStream is = new FileInputStream(file);
            is.read(getBytes);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getBytes;
    }*/
    /*public static void decoder(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.decode()
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }*/

//==============================카메라 실행 부분============================

    //카메라 실행
    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    //이미지 블러오기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegrees(exifOrientation);
            } else {
                exifDegree = 0;
            }

            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(rotate(bitmap, exifDegree));
        }
    }

    //이미지 파일 생성
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        pic = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = pic.getAbsolutePath();
        return pic;
    }
    //사진 회전
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    //GPS=============================================
    private void startLocationService() {
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsListener = new GPSListener();
        long minTime = 1000;
        float minDistance = 0;
        try {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();
                //textView.setText("내 위치 : " + latitude + ", " + longitude);
            }

        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
    private class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            String msg = "Latitude : "+ lat + "\nLongitude:"+ lon; Log.i("GPSListener", msg);
            //textView.setText("내 위치는 : " + latitude + ", " + longitude);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            manager.removeUpdates(gpsListener);

        }
        public void onProviderDisabled(String provider) {

        } public void onProviderEnabled(String provider) {

        } public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}
