package org.andriodtown.health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_findPw;
    private EditText edit_id;
    private EditText edit_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_login = findViewById(R.id.btn_login);
        btn_findPw = findViewById(R.id.btn_findPw);
        edit_id = findViewById(R.id.edit_id);
        edit_pw = findViewById(R.id.edit_pw);
    }

    public void login(View v){
        boolean id = edit_id.getText().toString().equals(SharedPreferenceManager.getInstance().getId());
        boolean pw = edit_pw.getText().toString().equals(SharedPreferenceManager.getInstance().getPw());
        if(id&&pw==true){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}