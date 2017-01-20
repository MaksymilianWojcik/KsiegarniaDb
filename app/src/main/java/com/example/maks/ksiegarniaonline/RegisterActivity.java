package com.example.maks.ksiegarniaonline;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @BindView(R.id.registerLogin)
    EditText registerLoginText;

    @BindView(R.id.registerPassword)
    EditText registerPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        dbHelper = new DBHelper(this);

    }

    @OnClick(R.id.buttonLogin)
    public void goBack(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.buttonRegister)
    public void registerUser (){
        String login = registerLoginText.getText().toString();
        String password = registerPasswordText.getText().toString();

        if(isDataOk(login, password)) {
            if (!dbHelper.isUserinDb(login)) {
                dbHelper.addUser(login, password);
            } else {
                Log.e("Register", "Login zajety!");
                Toast.makeText(this, "Login zajety!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("Register", "Zle dane!");
            Toast.makeText(this, "Zle wpisane dane", Toast.LENGTH_SHORT).show();
        }

    }


    public boolean isDataOk(String login, String password){
        if ((login.length() > 4 && login.length() < 8) && (password.length() > 4 && password.length() < 8)){
            return true;
        } else {
            return false;
        }
    }


}
