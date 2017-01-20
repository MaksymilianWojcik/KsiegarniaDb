package com.example.maks.ksiegarniaonline;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loginEditText)
    EditText loginText;

    @BindView(R.id.passwordEditText)
    EditText passwordText;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        db = new DBHelper(this);
    }

    @OnClick(R.id.buttonRegister)
    public void registerActivity(){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.buttonLogin)
    public void login(){
        db.getUsers();
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        boolean doesUserExist = isUserExist(login, password);


        if(doesUserExist){
            Intent libraryIntent = new Intent(this, Library.class);
            startActivity(libraryIntent);
        } else {
            Log.e("Logowanie", "Nie udalo sie zalogowac");
            Toast.makeText(this, "Nie udalo sie zalogowac", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isUserExist(String login, String password){

        List<User> uzytkownicy = db.getUsers();
        for(User user : uzytkownicy){
            Log.e("uzytkownik", user.toString());
            if(user.getUserLogin().equals(login) && user.getUserPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_info:
                Toast.makeText(this, "Kliknales info", Toast.LENGTH_SHORT).show();
                Intent infoIntent = new Intent(this, InfoActivity.class);
                startActivity(infoIntent);

                return true;
            case R.id.item_user:
                Toast.makeText(this, "Kliknales user", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
