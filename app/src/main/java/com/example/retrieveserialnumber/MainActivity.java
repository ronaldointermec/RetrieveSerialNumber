package com.example.retrieveserialnumber;

import static android.os.Environment.getExternalStoragePublicDirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.honeywell.osservice.sdk.DeviceManager;
import com.honeywell.osservice.sdk.HonOSException;

public class MainActivity extends AppCompatActivity {
    private DeviceManager deviceManager;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deviceManager = DeviceManager.getInstance(this);

        ActivityCompat.requestPermissions(this,
                new String[]{
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        imageView = findViewById(R.id.imageView);

    }

    public void getSerialNumber(View v) {

        if (deviceManager.isReady()) {
            try {

                Log.d("Demo", "getSerialNumber: " + deviceManager.getSerialNumber());
            } catch (HonOSException e) {
                e.printStackTrace();
                Log.e("Demo", e.getErrorCode() + " " + e.getMessage());
            }
        }
    }

    public void getImage(View v){

                try{

                    String stringPath = "/storage/emulated/0/Download/images.png";

                    Bitmap bitmap = BitmapFactory.decodeFile(stringPath);

                    imageView.setImageBitmap(bitmap);
                    Log.d("Demo", "deu certo");
                                   }
                catch (Exception e){
                    e.printStackTrace();
                    Log.e("Demo", "getImage: "+e.getMessage());
                }
    }

}