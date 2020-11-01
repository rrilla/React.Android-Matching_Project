package com.project.matchingapp3.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.ImageUpload;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {

    EditText etId, etPw, etPw2, etName, etNickname, etPhone, etEmail, etLocation;
    Button btnUploadImg, btnSelectImg, btnJoin;
    ImageView ivSelectImg;

    private final int REQ_CODE_SELECT_IMAGE = 100;
    private String img_path = new String();
    private Bitmap image_bitmap_copy = null;
    private Bitmap image_bitmap = null;
    private String imageName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        etId = findViewById(R.id.join_et_id);
        etPw = findViewById(R.id.join_et_pw);
        etPw2 = findViewById(R.id.join_et_pw2);
        etName = findViewById(R.id.join_et_name);
        etNickname = findViewById(R.id.join_et_nickname);
        etPhone = findViewById(R.id.join_et_phone);
        etEmail = findViewById(R.id.join_et_email);
        etLocation = findViewById(R.id.join_et_location);
        btnUploadImg = findViewById(R.id.join_btn_imageUpload);
        btnSelectImg = findViewById(R.id.join_btn_imageSelect);
        btnJoin = findViewById(R.id.join_btn_join);
        ivSelectImg = findViewById(R.id.join_iv_image);

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent를 통해 이미지를 선택
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUpload imgUpload = new ImageUpload();
                imgUpload.execute("imgUpload", img_path);
                Toast.makeText(getApplicationContext(), "이미지 전송 성공", Toast.LENGTH_SHORT).show();
                Log.d("test-이미지업로드", "Success");
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String[] result = new String[1];
                RestAPITask task = new RestAPITask("join");
                User user = new User();

                user.setLoginid(etId.getText().toString());
                user.setPassword(etPw.getText().toString());
                user.setUsername(etName.getText().toString());
                user.setNickname(etNickname.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setLocation(etLocation.getText().toString());

                try {
                    result = task.execute(gson.toJson(user)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("test",result[0]);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(getBaseContext(), "resultCode : " + data, Toast.LENGTH_SHORT).show();

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    img_path = getImagePathToUri(data.getData()); //이미지의 URI를 얻어 경로값으로 반환.
                    Toast.makeText(getBaseContext(), "img_path : " + img_path, Toast.LENGTH_SHORT).show();
                    //이미지를 비트맵형식으로 반환
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    //사용자 단말기의 width , height 값 반환
                    int reWidth = (int) (getWindowManager().getDefaultDisplay().getWidth());
                    int reHeight = (int) (getWindowManager().getDefaultDisplay().getHeight());

                    //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                    image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                    ivSelectImg.setImageBitmap(image_bitmap_copy);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getImagePathToUri(Uri data) {
        //사용자가 선택한 이미지의 정보를 받아옴
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.d("test", imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
        Toast.makeText(getApplicationContext(), "이미지 이름 : " + imgName, Toast.LENGTH_SHORT).show();

        this.imageName = imgName;

        return imgPath;
    }//end of getImagePathToUri()


}