package org.andriodtown.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity{
    // 앱 실행될 때 로고 띄어주기(3초간)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // try catch로 thread에러를 탐지
        // 정상실행 될 때
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // 에러 났을 때(안드로이드 스튜디오에 에러 메시지 띄움)
            e.printStackTrace();
        }
        //intent(지금 나의 화면, 넘어갈 화면)
        Intent intent = new Intent(this,MainActivity.class);
        // Activity A->B로 이동할 때(A->B->A돌아와도 B에서 넘겨줄 것 없을 때)
        startActivity(intent);
        // Activty A->B->A로 돌아올 때 B에서 뭔가 처리한 결과를 A에 넘겨줘야 할 때
        //startActivityForResult();

        // Activity를 메모리상에서 지우는 코드
        // 안해주면 뒤로가기 눌렀을 때 이 화면 다시 나옴
        finish();
    }
}
