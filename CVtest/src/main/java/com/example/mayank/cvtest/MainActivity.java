package com.example.mayank.cvtest;


import android.content.ContentValues;
import android.content.res.AssetManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import org.opencv.core.*;
import org.opencv.android.*;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
public class MainActivity extends AppCompatActivity {
    FrameLayout frame;
    GestureDetector mGestureDetector;
    String TAG = "GestureListenerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (FrameLayout) findViewById(R.id.fram);

        mGestureDetector = new GestureDetector(this, mGestureListener);

        frame.setOnTouchListener(new FrameLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent asd= new Intent(MainActivity.this,secpage.class);
                startActivity(asd);
                return mGestureDetector.onTouchEvent(event);

            }
        });
    }

    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        int swipe_Min_Distance = 100;
        int swipe_Max_Distance = 350;
        int swipe_Min_Velocity = 100;

        public boolean onDown(MotionEvent e) {
            return true;
        }
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "[CALLBACK_GL] boolean onFling(e1:" + e1 + ", e2:" + e2 + ", velocityX:" + velocityX
                    + ", velocityY:" + velocityY + "");

            final float xDistance = Math.abs(e1.getX() - e2.getX());
            final float yDistance = Math.abs(e1.getY() - e2.getY());

            if(xDistance > this.swipe_Max_Distance || yDistance > this.swipe_Max_Distance)
                return false;

            velocityX = Math.abs(velocityX);
            velocityY = Math.abs(velocityY);
            boolean result = false;

            if(velocityX > this.swipe_Min_Velocity && xDistance > this.swipe_Min_Distance){
                if(e1.getX() > e2.getX()) // right to left
                    Log.i(TAG, "Swipe Left");

                else
                    Log.i(TAG, "Swipe Right");
            }

            //return super.onFling(e1, e2, velocityX, velocityY);
            return true;
        }
    };
}


