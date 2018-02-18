package org.andriodtown.health;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btn_profile;
    private ImageView img_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView(){
        btn_profile = findViewById(R.id.btn_profile);
        img_profile = findViewById(R.id.img_profile);
        registerForContextMenu(btn_profile);
    }

    public void addProfile(View v){
        openContextMenu(v);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("프로필 사진 설정");
        menu.add(0,1,100,"갤러리에서 불러오기");
        menu.add(0,2,200,"사진 촬영");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1 :
                String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
                PermissionUtil permissionUtil = new PermissionUtil(111, permission);

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 111);
                return true;
            case 2 :
                return true;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri imageUri = null;
            switch (requestCode){
                case 111 :
                    if(data!=null){
                        imageUri = data.getData();
                        Glide.with(this).load(imageUri).apply(new RequestOptions().circleCrop()).into(img_profile);
                    }
                    break;

            }
        }
    }
}
