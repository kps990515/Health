package org.andriodtown.health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_findPw;
    private EditText edit_id;
    private EditText edit_pw;
    private ImageView img_id;
    private ImageView img_pw;
    private boolean checkId=false;
    private boolean checkPw=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initView() {
        btn_login = findViewById(R.id.btn_login);
        btn_findPw = findViewById(R.id.btn_findPw);
        edit_id = findViewById(R.id.edit_id);
        edit_pw = findViewById(R.id.edit_pw);
        img_id = findViewById(R.id.img_id);
        img_pw = findViewById(R.id.img_pw);
        btn_login.setEnabled(false); // 초기에 로그인버튼 비활성화
    }

    private void initListener() {
        // edit_id가 입력받는 내용을 실시간 감시
        edit_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!checkEmail(s.toString())){ // edit_id에 입력한 값이 이메일 형식과 일치하지 않을 때
                    img_id.setVisibility(View.VISIBLE);
                    checkId=false;
                    enableButton(checkId, checkPw);

                }else{
                    img_id.setVisibility(View.INVISIBLE);
                    checkId=true;
                    enableButton(checkId, checkPw);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){ // onTextChanged가 실행되고 edit_id에 입력한 값을 유저가 다 지웠을 때(에러메시지 없애주기 위하여)
                    img_id.setVisibility(View.INVISIBLE);
                }
            }
        });
        edit_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!checkPw(s.toString())){ // edit_id에 입력한 값이 이메일 형식과 일치하지 않을 때
                    img_pw.setVisibility(View.VISIBLE);
                    checkPw=false;
                    enableButton(checkId, checkPw);
                }else{
                    img_pw.setVisibility(View.INVISIBLE);
                    checkPw=true;
                    enableButton(checkId, checkPw);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){ // onTextChanged가 실행되고 edit_id에 입력한 값을 유저가 다 지웠을 때(에러메시지 없애주기 위하여)
                    img_pw.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void enableButton(boolean id, boolean pw){
        // id & pw형식이 맞고 틀리고에 따라 login버튼 활성/비활성화 시켜주는 함수
        if(id&&pw==true){
            btn_login.setEnabled(true);
        }else{
            btn_login.setEnabled(false);
        }
    }

    public void login(View v){
        boolean id = edit_id.getText().toString().equals(SharedPreferenceManager.getInstance().getId());
        boolean pw = edit_pw.getText().toString().equals(SharedPreferenceManager.getInstance().getPw());
        if(id&&pw==true){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkEmail(String email){

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    private boolean checkPw(String pw) {
        String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{8,16}$";
        Pattern pattern = Pattern.compile(Passwrod_PATTERN);
        Matcher matcher = pattern.matcher(pw);
        return matcher.matches();
    }

}