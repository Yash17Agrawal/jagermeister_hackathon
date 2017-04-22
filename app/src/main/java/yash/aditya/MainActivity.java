package yash.aditya;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;

import yash.aditya.Chatbot.Chat;

public class MainActivity extends AppCompatActivity {

    LinearLayout something_new,chat_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        something_new=(LinearLayout)findViewById(R.id.something_new);
        chat_chat=(LinearLayout)findViewById(R.id.chat_chat);
        try {
            Glide.with(this).load(R.drawable.profile_cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        chat_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, Chat.class);
                startActivity(i);
            }
        });

    }



    public void something_new_click(View view)
    {
        Intent i=new Intent(MainActivity.this,Search.class);
        startActivity(i);

    }
}
