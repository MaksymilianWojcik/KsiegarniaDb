package com.example.maks.ksiegarniaonline;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Library extends AppCompatActivity {

    @BindView(R.id.fragmentFrame)
    FrameLayout fragment;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentFrame, new MyBooksFragment());
        fragmentTransaction.commit();
    }

    @OnClick(R.id.ksiazkiButton)
    public void myBooks(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentFrame, new MyBooksFragment());
        transaction.commit();
    }

    @OnClick(R.id.uzytkownicyButton)
    public void usersBooks(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentFrame, new usersFragment());
        transaction.commit();
    }

    @OnClick(R.id.bibliotekaButton)
    public void libraryBooks(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentFrame, new libraryFragment());
        transaction.commit();
    }

    @OnClick(R.id.logoutButton)
    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
