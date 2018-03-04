package org.andriodtown.health;

import android.Manifest;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btn_profile;
    private ImageView img_profile;
    private Uri fileUri = null;

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
                permissionUtil.check(this, new PermissionUtil.IPermissionGrant() {
                    @Override
                    public void run() {
                        gallery();
                    }

                    @Override
                    public void fail() {

                    }
                });

                return true;
            case 2 :
                String[] perm = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                PermissionUtil permUtil = new PermissionUtil(222, perm);
                permUtil.check(this, new PermissionUtil.IPermissionGrant() {
                    @Override
                    public void run() {
                        camera();
                    }

                    @Override
                    public void fail() {

                    }
                });
                return true;

        }
        return super.onContextItemSelected(item);
    }

    private void gallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 111);
    }

    private void camera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            try {
                File cameraFile = createFile();
                refreshMedia(cameraFile);
                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", cameraFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, 222);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            startActivityForResult(intent, 222);
        }
    }

    private void refreshMedia(File file){
        MediaScannerConnection.scanFile(
                this, new String[]{file.getAbsolutePath()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {

                    }
                }
        );
    }

    private File createFile() throws IOException {
        String fileName = "Temp" + System.currentTimeMillis(); // 내컴퓨터/C드라이브
        File dir = new File(Environment.getExternalStorageDirectory()+File.separator+"Health"+File.separator);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File imageFile = File.createTempFile(fileName, ".jpg",dir);
        return imageFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 111:
                    if (resultCode == RESULT_OK) {
                        Uri imageUri = null;
                        if (data != null) {
                            imageUri = data.getData();
                            Glide.with(this).load(imageUri).apply(new RequestOptions().circleCrop()).into(img_profile);
                        }
                        break;
                    }
                case 222:
                    if (resultCode == RESULT_OK) {
                        Uri imageUri = null;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            imageUri = data.getData();
                        } else {
                            imageUri = fileUri;
                        }
                            Glide.with(this).load(imageUri).apply(new RequestOptions().circleCrop()).into(img_profile);
                        break;
                    }
            }
    }
}
