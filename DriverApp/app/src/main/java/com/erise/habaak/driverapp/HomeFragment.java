package com.erise.habaak.driverapp;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habaa.driverapp.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Server server;
    private String url = "http://192.168.0.24/first/admin/";
    private String cardUrl;
    private static TextView tvHumiData;
    private static TextView tvTempData;
    private static TextView tvDistanceData;
    private static TextView tvBusNum;
    private static Chronometer chronometerDriveTimeData;
    private ImageButton imgbtnLogout;
    private ImageView ivLogo;
    //private RecyclerView recyclerView;
    private WebView wvCards;
    private FrameLayout flhome;
    //private RecyclerAdapter recyclerAdapter;

    private SharedPreferences pref;

    private String engineLoadValue;
    private String engineCoolantTemperature;
    private String enginRPM;
    private String MAF;
    private String throttlePosition;

    private String reqTemp;

    private static String busidx;
    private static String busNum, platenum, driverName;

    private boolean canDataFlag[] = {false, false, false, false, false};


    String lon, lat;
    //private List<Recycler_item> cards;

    private TimerTask timerTempHumiTask;
    private TimerTask speedTask;
    private TimerTask locationTask;
    private TimerTask engineLoadValueTask;
    private TimerTask engineCoolantTemperatureTask;
    private TimerTask enginRPMTask;
    private TimerTask vehicleSpeedTask;
    private TimerTask MAFTask;
    private TimerTask throttlePositionTask;
    private TimerTask CanDataHttpTask;
    private TimerTask reqTempChkTask;

    private Timer timer;

    private LatLonSender latLonSender;
    private TempReqGetHttp tempReqGetHttp;
    private CanDataHttpSender canDataHttpSender;
    private SendTempHumiHttp sendTempHumiHttp;
    private SendSpeedHttp sendSpeedHttp;
    private LocationManager manager;
    private GPSListener gpsListener;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        busidx = pref.getString("BUSIDX", "");
        busNum = pref.getString("NUM", "");
        platenum = pref.getString("PLATENUM", "");
        driverName = pref.getString("NAME", "");
        Log.i("[BUS IDX CHECKER]",busidx);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        //Log.i("[busidx]",LoginActivity.resbusidx);
        //add(new BasicNameValuePair("busidx",LoginActivity.resbusidx));
        try {
            server = new Server();
            server.start();
//Timer
            timerTempHumiTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendTempHumi();
                }
            };

            /*speedTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendSpeed();
                }
            };*/

            engineLoadValueTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("engineLoadValue");
                }
            };
            engineCoolantTemperatureTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("engineCoolantTemperature");
                }
            };
            enginRPMTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("enginRPM");
                }
            };
            vehicleSpeedTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("vehicleSpeed");
                }
            };
            MAFTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("MAF");
                }
            };
            throttlePositionTask = new TimerTask() {
                @Override
                public void run() {
                    server.sendCanData("throttlePosition");
                }
            };
            locationTask = new TimerTask() {
                @Override
                public void run() {
                    latLonSender = (LatLonSender) new LatLonSender().execute(url + "relocation.do", lat, lon);
                }
            };

            reqTempChkTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i("[CONTROL TEMP CHK]", "ctrl req start");
                    tempReqGetHttp = (TempReqGetHttp) new TempReqGetHttp().execute(url+"recontroltemp.do", busidx);
                    server.sendCanData(reqTemp);
                }
            };


            CanDataHttpTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i("[CanData http check]","chk"+busidx);
                    if (canDataFlag[0] == true && canDataFlag[1] == true && canDataFlag[2] == true && canDataFlag[3] == true && canDataFlag[4] == true) {
                        canDataHttpSender = (CanDataHttpSender) new CanDataHttpSender().execute(url + "recandata.do?", engineLoadValue, engineCoolantTemperature, enginRPM, MAF, throttlePosition, busidx);
                    }
                }
            };


            timer = new Timer();
            timer.schedule(timerTempHumiTask, 0, 8811);
            //timer.schedule(speedTask,0,3000);
            timer.schedule(engineLoadValueTask, 0, 15000);
            timer.schedule(engineCoolantTemperatureTask, 0, 15500);
            timer.schedule(enginRPMTask, 0, 16000);
            timer.schedule(vehicleSpeedTask, 0, 3000);
            timer.schedule(MAFTask, 0, 17000);
            timer.schedule(throttlePositionTask, 0, 17500);
            timer.schedule(locationTask, 0, 5000);
            timer.schedule(CanDataHttpTask, 0, 35000);
            timer.schedule(reqTempChkTask,10000,30000);
            startLocationService();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //  TCP IP
        flhome = v.findViewById(R.id.flhome);
        tvTempData = v.findViewById(R.id.tvTempData);
        tvHumiData = v.findViewById(R.id.tvHumiData);
        tvDistanceData = v.findViewById(R.id.tvDistanceData);
        chronometerDriveTimeData = v.findViewById(R.id.chronometerDriveTimeData);
        imgbtnLogout = v.findViewById(R.id.imgbtnLogout);
        ivLogo = v.findViewById(R.id.ivLogo);
        tvBusNum = v.findViewById(R.id.tvBusNum);
        wvCards = v.findViewById(R.id.wvCards);

        wvCards.getSettings().setJavaScriptEnabled(true);
        //wvCards.setWebViewClient(new CardWebViewClient());
        if(cardUrl == null){
            cardUrl = url+"clientmsg.do";
        }
        wvCards.loadUrl(cardUrl);
        Log.i("[Card Url]",wvCards.toString());

        chronometerDriveTimeData.start();

        //  RecyclerView
//        recyclerView = v.findViewById(R.id.recyclerview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//
//        List<Recycler_item> cards = new ArrayList<>();
//        cards.add(new Recycler_item(R.drawable.ic_warning, "경고", "12:00"));
//        recyclerAdapter = new RecyclerAdapter(getActivity().getApplicationContext(), cards, R.layout.activity_main);

        tvBusNum.setText(busNum+"번");

        imgbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutHttp logoutHttp = (LogoutHttp) new LogoutHttp().execute(url + "driverlogout.do", busidx);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




//CARD VIEW UPDATE SET
    /*private ArrayList<Recycler_item> getCards(){
        ArrayList<Recycler_item> Cards = new ArrayList<>();

    }*/
    private class CardWebViewClient extends WebViewClient {
    /*@Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        cardUrl = url;
        view.loadUrl(url);
        Log.d("[WebView chek]",url);
        return true;
    }*/
}

/*
    public List cardViewUpdate(String mode) {

        */
/*Recycler_item[] card = new Recycler_item[5];
        card[0] = new Recycler_item(R.drawable.ic_warning, "경고", getTime());
        card[1] = new Recycler_item(R.drawable.ic_speed, "과속", getTime());
        card[2] = new Recycler_item(R.drawable.ic_temperature_hot, "온도 하강 요청", getTime());
        card[3] = new Recycler_item(R.drawable.ic_temperature_cold, "온도 상승 요청", getTime());
        card[4] = new Recycler_item(R.drawable.ic_police, "범죄발생", getTime());*//*

        Log.i("[Card]", "cardViewUpdate");
        switch (mode) {
            case "caution"://경고

                break;
            case "hot"://더워

                break;
            case "cold"://추워

                break;
            case "fast"://빨라

                break;
            case "crime"://범죄

                break;
        }
        return cards;
    }
*/

//CAN DATA NETWORKING SET


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);
        return formatDate;
    }

    //  TCP IP Server
/*
    온도
*/
    public void setTempView(final String humidNum,final String tempNum) {
        Log.i("[setTempView]", tempNum + " : " + humidNum);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        tvTempData.setText(tempNum+" °C");
                        tvHumiData.setText(humidNum+" %");
                    }
                });
            }
        }).start();

    }

    public void setDistanceView(final String distance) {
        Log.i("[setDistanceView]", distance);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        tvDistanceData.setText(distance+" Km");
                    }
                });
            }
        }).start();

    }


    public class Server extends Thread {

        ServerSocket serverSocket;
        boolean flag = true;
        boolean rflag = true;
        ArrayList<DataOutputStream> list = new ArrayList<>();

        Server() throws IOException {
            serverSocket = new ServerSocket(8888);
            Log.i("[Server]", "Server Ready...");
        }

        //start server
        @Override
        public void run() {
            //Accept Client Connection...
            try {
                while (flag) {
                    Log.i("[Server]", "Ready Accept");
                    Socket socket = serverSocket.accept();
                    String client = socket.getInetAddress().getHostAddress();
                    String clientHostName = socket.getInetAddress().getHostName();
                    Log.i("[Client]", client);
                    Log.i("[clientHostName]", socket.getInetAddress().toString());

                    new Receiver(socket).start();
                    //new Sender();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class Receiver extends Thread {
            InputStream is;
            DataInputStream dis;
            OutputStream os;
            DataOutputStream dos;
            int count = 1;
            Socket socket;// Thread가 끝날때 socket을 죽이기 위해

            Receiver(Socket socket) {
                try {
                    this.socket = socket;
                    is = socket.getInputStream();
                    dis = new DataInputStream(is);
                    os = socket.getOutputStream();
                    dos = new DataOutputStream(os);
                    list.add(dos);
                    Log.d("[Server]", "Connected Count : " + list.size());
                } catch (IOException e) {
                    // TODO Auto-generated catch block2
                    e.printStackTrace();
                }
            }


            @Override
            public void run() {

                try {
                    //client가 보내는 메시지를 받는다.
                    while (rflag) {
                        if (socket.isConnected() && dis != null & dis.available() > 0) {
                            String str = dis.readUTF();
                            String tempData = "";
                            String humiData = "";
                            String busAndDriverInfo = "";

                            Log.i("[Receive MSG]", str);
                            //조건문으로 통신 값 분류

                            StringTokenizer st = new StringTokenizer(str, " ");
                            String[] tokenBox = new String[10];
                            for (int x = 0; st.hasMoreElements(); x++) {
                                //Log.i("[Token]" , x + " : " + st.nextToken() );
                                tokenBox[x] = st.nextToken();
                                Log.i("[Receive MSG array]", tokenBox[x]);
                            }

                            //latLonSender = (LatLonSender) new LatLonSender().execute(url+"relocation.do",lat,lon);
                            //HTTP
                            if (tokenBox[0].equals("can")) {
                                switch (tokenBox[1]) {
                                    case "engineLoadValue":
                                        canDataFlag[0] = true;
                                        engineLoadValue = tokenBox[2];
                                        break;
                                    case "engineCoolantTemperature":
                                        canDataFlag[1] = true;
                                        engineCoolantTemperature = tokenBox[2];
                                        break;
                                    case "enginRPM":
                                        canDataFlag[2] = true;
                                        enginRPM = tokenBox[2];
                                        //sendCanData(tokenBox[2]);
                                        break;
                                    case "vehicleSpeed":
                                        //speed http
                                        sendSpeedHttp = (SendSpeedHttp) new SendSpeedHttp().execute(url + "respeed.do?", tokenBox[2], busidx);
                                        sendSpeedDataClient(tokenBox[2]);

                                        setDistanceView(tokenBox[2]);
                                        if( Integer.parseInt(tokenBox[2])>=30 ){
                                            //wvCards.setVisibility(View.INVISIBLE);
                                        }
                                        break;
                                    case "MAF":
                                        canDataFlag[3] = true;
                                        MAF = tokenBox[2];
                                        //sendCanData(tokenBox[2]);
                                        break;
                                    case "throttlePosition":
                                        canDataFlag[4] = true;setDistanceView(tokenBox[2]);
                                        throttlePosition = tokenBox[2];
                                        //sendCanData(tokenBox[2]);
                                        break;
                                    case "Humidity":
                                        Log.i("[TEMP TEST]", tokenBox[2]);


                                        sendTempHumiHttp = (SendTempHumiHttp) new SendTempHumiHttp().execute(url + "retemp.do?", tokenBox[2], tokenBox[4], busidx);
                                        sendTempHumiDataClient(tokenBox[2],tokenBox[4]);

                                        setTempView(tokenBox[2], tokenBox[4]);
                                        break;
                                }
                            }
//client tcp req
                            else if (tokenBox[0].equals("client")) {

                                switch (tokenBox[1]) {
                                    case "conn":
                                        sendToUser(busAndDriverInfo);
                                        break;
                                    case "bell":
                                        Log.i("[bell]", "beep!!");
                                        sendCanData("bell");
                                        break;
                                    case "hot":
                                        Log.i("[hot]", "hot");
                                        //cardViewUpdate("hot");
                                        //recyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), cardViewUpdate("hot"), R.layout.activity_main));
                                        //Log.i("[Cards]", cards.toString());
                                        count++;
                                        SendPassengerReqHttp sendPassengerHotReqHttp = (SendPassengerReqHttp) new SendPassengerReqHttp().execute(url + "cardfromclient.do?", "1", busidx, String.valueOf(count));
                                        break;
                                    case "cold":
                                        Log.i("[cold]", "cold");
                                        //cardViewUpdate("cold");
                                        //Log.i("[Cards]", cards.toString());
                                        count++;
                                        SendPassengerReqHttp sendPassengerColdReqHttp = (SendPassengerReqHttp) new SendPassengerReqHttp().execute(url + "cardfromclient.do?", "2", busidx, String.valueOf(count));
                                        break;
                                    case "fast":
                                        Log.i("[fast]", "fast");
                                        //cardViewUpdate("fast");
                                        //Log.i("[Cards]", cards.toString());
                                        count++;
                                        SendPassengerReqHttp sendPassengerFastReqHttp = (SendPassengerReqHttp) new SendPassengerReqHttp().execute(url + "cardfromclient.do?", "3", busidx, String.valueOf(count));
                                        break;
                                    case "crime":
                                        Log.i("[crime]", "crime");
                                        //cardViewUpdate("crime");
                                        //Log.i("[Cards]", cards.toString());
                                        count++;
                                        SendPassengerReqHttp sendPassengerCrimeReqHttp = (SendPassengerReqHttp) new SendPassengerReqHttp().execute(url + "cardfromclient.do?", "4", busidx, String.valueOf(count));
                                        break;
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    list.remove(dos);
                    //Log.d("[Server App]",list.size());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if (dis != null) {
                        try {
                            dis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //SEND BUS AND DRIVER INFO TO PASSENGER
        void sendToUser(String msg) {
            Log.i("[BUS&DRIVER INFO]", "BUS&DRIVER INFO Sending...");
            BusAndDriverInfoSender sender = new BusAndDriverInfoSender();

            sender.start();
        }

        //SEND CAN REQ
        void sendTempHumi() {
            Log.i("[TEMPELATURE]", "TEMPELATURE Sending...");
            TempHumiSender sender = new TempHumiSender();
            //sender.setMsg(msg);
            sender.start();
        }

        void sendSpeedData(String msg) {
            Log.d("[Speed Data]", msg);
            CanDataSender sender = new CanDataSender();
            sender.setCanMsg(msg);
            sender.start();
        }

        void sendCanData(String msg) {
            Log.d("[CAN DATA]", " "+msg);
            CanDataSender sender = new CanDataSender();
            sender.setCanMsg(msg);
            sender.start();
        }
        void sendTempHumiDataClient(String humi, String temp){
            SendTempHumiClient sender = new SendTempHumiClient();
            sender.setTempHumi(humi, temp);
            sender.start();
        }
        void sendSpeedDataClient(String speed) {
            SpeedSender sender = new SpeedSender();
            sender.setSpeed(speed);
            sender.start();
        }
        //Send Message All Clients

        //온습도 sender
        class BusAndDriverInfoSender extends Thread {

            String msg = "info " + busNum + " " + platenum + " " + driverName;


            @Override
            public void run() {
                try {
                    if (!list.isEmpty() && list.size() >= 0) {
                        for (DataOutputStream dos : list) {
                            dos.writeUTF(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        class TempHumiSender extends Thread {
            /*String msg;
            public void setMsg(String msg) {
                this.msg = msg;
            }*/

            @Override
            public void run() {
                try {
                    if (!list.isEmpty() && list.size() >= 0) {
                        for (DataOutputStream dos : list) {
                            dos.writeUTF("H&T");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //속도 sender
        class SpeedSender extends Thread {
            String msg;
            public void setSpeed(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                try {
                    if (!list.isEmpty() && list.size() >= 0) {
                        for (DataOutputStream dos : list) {
                            dos.writeUTF("speed "+msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        class SendTempHumiClient extends Thread {

            String temp;
            String humi;
            void setTempHumi(String humi, String temp){
                this.humi = humi;
                this.temp = temp;
            }
            @Override
            public void run() {
                try {
                    if (!list.isEmpty() && list.size() >= 0) {
                        for (DataOutputStream dos : list) {
                            dos.writeUTF("temp "+temp+ " humi " + humi);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //CAN DATA
        class CanDataSender extends Thread {
            String msg;

            void setCanMsg(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                try {
                    if (!list.isEmpty() && list.size() >= 0) {
                        for (DataOutputStream dos : list) {
                            dos.writeUTF(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopServer() {
            rflag = false;
        }

    }


    //LAT, LON
    private void startLocationService() {

        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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
            String msg = "Latitude : " + lat + "\nLongitude:" + lon;
            Log.i("GPSListener", msg);
            //textView.setText("내 위치는 : " + latitude + ", " + longitude);
            Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            manager.removeUpdates(gpsListener);

        }

        public void onProviderDisabled(String provider) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }


    public class LatLonSender extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            getMainJson(strings[0], strings[1], strings[2]);
            return null;
        }

        private void getMainJson(String url, String lat, String lon) {

            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                Log.i("[LatLonURL]", url);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lat", lat));
                nameValuePairs.add(new BasicNameValuePair("lon", lon));
                nameValuePairs.add(new BasicNameValuePair("busidx", busidx));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE, "application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    Log.i("[Location JSON]", line);

                    JSONArray jsonArray = new JSONArray(line);
                    System.out.println("JSONArray -- " + jsonArray);
/*
                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String isSuccess = jsonObject.getString("MainSucess");
                        *//**//*cmt = jsonObject.getString("cmt");
                        uidx = jsonObject.getString("uidx");
                        pidx = jsonObject.getString("pidx");
                        date = jsonObject.getString("dt");
                        like = jsonObject.getString("heart");*//**//*
                        cmtArr[i] = jsonObject.getString("cmt");
                        uidxArr[i] = jsonObject.getString("uidx");
                        pidxArr[i] = jsonObject.getString("pidx");
                        dateArr[i] = jsonObject.getString("dt");
                        likeArr[i] = jsonObject.getString("heart");
                        //imgurlArr[i] = jsonObject.getString("img");
                    }
                    */
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }

        }
    }
// 관리자 요청 온도 받기
    public class TempReqGetHttp extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String reqTemp = getMainJson(strings[0], strings[1]);
            Log.i("[CONTROL TEMP CHK]", "chk "+reqTemp);
            if(Integer.parseInt(reqTemp)!=0) {
                server.sendCanData("RT " + reqTemp);
            }
            return reqTemp;
        }

    /*@Override
    protected void onPostExecute(String s) {
        reqTemp = s;
        if(Integer.parseInt(reqTemp)!=0){
            Log.i("[CONTROL TEMP CHK]", reqTemp);
            server.sendCanData("RT "+reqTemp);
        }
    }*/

    private String getMainJson(String url, String busidx) {
            String reqTemp="0";
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                Log.i("[LatLonURL]", url);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("busidx", busidx));
                nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE, "application/json"));

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";

                while ((line = rd.readLine()) != null) {
                    Log.i("[Location JSON]", line);

                    JSONArray jsonArray = new JSONArray(line);
                    System.out.println("JSONArray -- " + jsonArray);
                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        reqTemp = jsonObject.getString("control");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            return reqTemp;

        }
    }

}
//==================latlon Networking============================

class SendTempHumiHttp extends AsyncTask<String, Void, Void> {


    String tempNum;
    String HumidNum;
    HttpURLConnection urlConn;
    URL url;

    SendTempHumiHttp() {
    }

    public SendTempHumiHttp(String tempNum, String HumidNum) {
        HumidNum = this.HumidNum;
        tempNum = this.tempNum;

    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            String surl = params[0];
            StringBuffer buffer = new StringBuffer();
            buffer.append(surl);
            buffer.append("temp").append("=").append(params[1]).append("&");
            buffer.append("humid").append("=").append(params[2]).append("&");
            buffer.append("busidx").append("=").append(params[3]);
            surl = buffer.toString();
            Log.i("[URL-retemp]", surl);
            try {
                url = new URL(surl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class SendSpeedHttp extends AsyncTask<String, Void, Void> {

    String speed;
    HttpURLConnection urlConn;
    URL url;

    SendSpeedHttp() {
    }

    public SendSpeedHttp(String speed) {
        speed = this.speed;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            String surl = params[0];
            StringBuffer buffer = new StringBuffer();
            buffer.append(surl);
            buffer.append("vehiclespeed").append("=").append(params[1]).append("&");
            buffer.append("busidx").append("=").append(params[2]);
            surl = buffer.toString();
            Log.i("[URL-retemp]", surl);
            try {
                url = new URL(surl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class LogoutHttp extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        getMainJson(strings[0], strings[1]);
        return null;
    }

    private void getMainJson(String url, String busIdx) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            Log.i("[Logout]", url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("busidx", busIdx));
            nameValuePairs.add(new BasicNameValuePair(HTTP.CONTENT_TYPE, "application/json"));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class CanDataHttpSender extends AsyncTask<String, Void, String> {

    HttpURLConnection urlConn;
    URL url;

    @Override
    protected String doInBackground(String... param) {
        getMainJson(param[0], param[1], param[2], param[3], param[4], param[5], param[6]);
        return null;
    }

    private void getMainJson(String surl, String engineLoadValue, String engineCoolantTemperature, String enginRPM, String MAF, String throttlePosition, String busidx) {

        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append(surl);
            buffer.append("engineLoadValue").append("=").append(engineLoadValue).append("&");
            buffer.append("engineCoolantTemperature").append("=").append(engineCoolantTemperature).append("&");
            buffer.append("enginRPM").append("=").append(enginRPM).append("&");
            buffer.append("MAF").append("=").append(MAF).append("&");
            buffer.append("throttlePosition").append("=").append(throttlePosition).append("&");
            buffer.append("busidx").append("=").append(busidx);
            surl = buffer.toString();
            Log.i("[URL-CanData]", surl);
            try {
                url = new URL(surl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}

class SendPassengerReqHttp extends AsyncTask<String, Void, Void> {

    private String msg;
    private HttpURLConnection urlConn;
    private URL url;

    SendPassengerReqHttp() {
    }


    public SendPassengerReqHttp(String msg) {
        msg = this.msg;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            String surl = params[0];
            StringBuffer buffer = new StringBuffer();
            buffer.append(surl);
            buffer.append("msg").append("=").append(params[1]).append("&");
            buffer.append("busidx").append("=").append(params[2]).append("&");
            buffer.append("msgidx").append("=").append(params[3]);
            surl = buffer.toString();
            Log.i("[URL-retemp]", surl);
            try {
                url = new URL(surl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}




//Recycle item
/*
class Recycler_item {
    private int image;
    private String title;
    private String time;

    int getImage() {
        return this.image;
    }

    String getTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Recycler_item) {
            Recycler_item another = (Recycler_item) obj;
            return TextUtils.equals(this.title, another.title);
        }
        return false;
    }

    Recycler_item(int image, String title, String time) {

        this.image = image;
        this.title = title;
        this.time = time;
    }
}
*/




