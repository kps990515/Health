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

public class SignUpActivity extends AppCompatActivity {

    private boolean email = false;
    private boolean pw = false;
    private boolean pwcheck = false;
    private boolean fname = false;
    private boolean lname = false;
    private EditText edit_signEmail;
    private EditText edit_signPw;
    private EditText edit_checkPw;
    private EditText edit_fname;
    private EditText edit_lname;
    private Button btn_signinFinal;
    private ImageView img_email;
    private ImageView img_pw;
    private ImageView img_check;
    private Button btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        initListener();
    }

    private void initView(){
        edit_signEmail = findViewById(R.id.edit_signEmail);
        edit_signPw = findViewById(R.id.edit_signPw);
        edit_checkPw = findViewById(R.id.edit_checkPw);
        edit_fname = findViewById(R.id.edit_fname);
        edit_lname = findViewById(R.id.edit_lname);
        btn_signinFinal = findViewById(R.id.btn_signinFinal);
        img_email = findViewById(R.id.img_email);
        img_check = findViewById(R.id.img_check);
        img_pw = findViewById(R.id.img_pw);
    }

    private void initListener(){
        edit_signEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkEmail(s.toString())!=true){
                    img_email.setVisibility(View.VISIBLE);
                    email=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
                else{
                    img_email.setVisibility(View.INVISIBLE);
                    email=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    img_email.setVisibility(View.INVISIBLE);
                    email=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_signPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkPw(s.toString())!=true){
                    img_pw.setVisibility(View.VISIBLE);
                    pw=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    img_pw.setVisibility(View.INVISIBLE);
                    pw=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")) {
                    img_pw.setVisibility(View.INVISIBLE);
                    pw = false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_checkPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(edit_signPw.getText().toString())){
                    img_check.setVisibility(View.INVISIBLE);
                    pwcheck=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    img_check.setVisibility(View.VISIBLE);
                    pwcheck=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    lname=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    lname=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
        edit_fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    fname=true;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }else{
                    fname=false;
                    enableLogin(email,pw,pwcheck,lname,fname);
                }
            }
        });
    }

    public void profile(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch(resultCode){
                case 999:

            }
        }
    }


    private void enableLogin(boolean email, boolean pw, boolean pwcheck, boolean lname, boolean fname){
        if(email&&pw&&pwcheck&&fname&&lname==true){
            btn_signinFinal.setEnabled(true);
        }else{
            btn_signinFinal.setEnabled(false);
        }
    }

    private boolean checkEmail(String email){

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    private boolean checkPw(String str) {
        String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{8,16}$";
        Pattern pattern = Pattern.compile(Passwrod_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
