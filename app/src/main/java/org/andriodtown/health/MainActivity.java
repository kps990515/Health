package org.andriodtown.health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // private -> 해당 클래스에서만 사용 가능하게 막아주는 접근제한자
    Button btn_login;
    Button btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // 자바 클래스의 btn_login과 디자인의 btn_login이 같은 것이라는 것을 알려주는 코드
        btn_login = findViewById(R.id.btn_login);
        btn_signUp = findViewById(R.id.btn_signUp);
    }

    public void login(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}
