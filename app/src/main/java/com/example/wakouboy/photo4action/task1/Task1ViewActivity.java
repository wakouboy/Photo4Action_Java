package com.example.wakouboy.photo4action.task1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wakouboy.photo4action.Nanostate;
import com.example.wakouboy.photo4action.R;
import com.example.wakouboy.photo4action.utils.Body;
import com.example.wakouboy.photo4action.utils.MyMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by wakouboy on 2017/2/11.
 */

public class Task1ViewActivity extends Activity{
    private static int REQUEST_THUMBNAIL = 1;// 请求缩略图信号标识
    private static int REQUEST_ORIGINAL = 2;// 请求原图信号标识
    private ImageView mImageView;
    private String sdPath;
    private String picPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        ImageView imageViewAdd = (ImageView) findViewById(R.id.imageViewAdd);
        imageViewAdd.setImageResource(R.mipmap.add);
        try {
            Nanostate nanostate = Nanostate.getNanostate();
            nanostate.send(new MyMessage("iPhone", "GetGraphLayout", new Body()));
        } catch (Exception e){
            e.printStackTrace();
        }
        sdPath = Environment.getExternalStorageDirectory().getPath();
        picPath = sdPath + "/" + "temp.png";
        mImageView =  (ImageView) findViewById(R.id.imageViewMatch);
        Log.e("sdPath1",sdPath);
        imageViewAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_THUMBNAIL);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_THUMBNAIL) {//对应第一种方法
                /**
                 * 通过这种方法取出的拍摄会默认压缩，因为如果相机的像素比较高拍摄出来的图会比较高清，
                 * 如果图太大会造成内存溢出（OOM），因此此种方法会默认给图片尽心压缩
                 */
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                mImageView.setImageBitmap(bitmap);
            }
            else if(resultCode == REQUEST_ORIGINAL){//对应第二种方法
                /**
                 * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
                 */
                FileInputStream fis = null;
                try {
                    Log.e("sdPath2",picPath);
                    //把图片转化为字节流
                    fis = new FileInputStream(picPath);
                    //把流转化图片
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    mImageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        fis.close();//关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
