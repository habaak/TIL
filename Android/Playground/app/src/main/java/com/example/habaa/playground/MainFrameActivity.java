package com.example.habaa.playground;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFrameActivity extends AppCompatActivity {
    MainFragment mainFragment;
    UploadFragment uploadFragment;
    MypageFragment mypageFragment;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(
                    R.id.contents,
                    new MainFragment()).commit();
            //main에다가 새로운 화면으로 진행시켜서 커밋해라!
        }
    }

    public void clickbt(View v) {
        if (v.getId() == R.id.btnMain) {
            manager.beginTransaction().replace(
                    R.id.contents,
                    mainFragment
            ).commit();
        } else if (v.getId() == R.id.btnUpload) {
            manager.beginTransaction().replace(
                    R.id.contents,
                    uploadFragment
            ).commit();
        } else if (v.getId() == R.id.btnMypage) {
            manager.beginTransaction().replace(
                    R.id.contents,
                    mypageFragment
            ).commit();

        }
    }
//=================하단 메뉴======================
    public static class MainFragment extends android.support.v4.app.Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v=inflater.inflate(R.layout.activity_main,container,false);

            return v;
            //메인레이아웃이만들어져서 프레임레이아웃에 붙여넣기됨.
        }
    }
    public static class UploadFragment extends android.support.v4.app.Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v=inflater.inflate(R.layout.activity_post_contents,container,false);
            return v;
            //메인레이아웃이만들어져서 프레임레이아웃에 붙여넣기됨.
        }
    }
    public static class MypageFragment extends android.support.v4.app.Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v=inflater.inflate(R.layout.activity_my_page,container,false);
            return v;
            //메인레이아웃이만들어져서 프레임레이아웃에 붙여넣기됨.
        }
    }
}
