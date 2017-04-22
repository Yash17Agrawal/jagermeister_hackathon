package com.example.mayank.cvtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Mayank on 12/4/2016.
 */
public class thirdpage extends AppCompatActivity implements View.OnClickListener {
    Button button;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab21,fab22,fab23;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    TextToSpeech t1;
    EditText ed1;
    Button b1;
    public int flag = 1;
    Button b;
    public String s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_show);

        s = getIntent().getStringExtra("deepali");

        EditText tv = (EditText) findViewById(com.example.mayank.cvtest.R.id.TEXT_STATUS_ID);
        tv.setText(s);

       // b1=(Button)findViewById(R.id.bb1);



     /*   b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = s;
                //new sing().execute(toSpeak);

                //while(ma.flag==1);

                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                Log.e("flag","1");

            }

        });*/

        b = (Button)findViewById(R.id.but);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(thirdpage.this, );
            }
        });
        fab21 = (FloatingActionButton)findViewById(R.id.fab21);
        fab22 = (FloatingActionButton)findViewById(R.id.fab22);
        fab23 = (FloatingActionButton)findViewById(R.id.fab23);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        fab21.setOnClickListener(this);
        fab22.setOnClickListener(this);
        fab23.setOnClickListener(this);


    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.e("the id is of the view",id+"");
        switch (id){
            case R.id.fab21:
                animateFAB();
                break;

            case R.id.fab22:
                Log.e("reaching the fab23","hi reached 23");
                new HttpAsyncTask().execute();
                break;

            case R.id.fab23:
                t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.UK);

                            flag = 0;

                        }
                    }
                });
                String toSpeak = s;
                //new sing().execute(toSpeak);

                //while(ma.flag==1);

                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                Log.e("flag","1");
                Log.e("the flag thing","flag");
                break;

        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab21.startAnimation(rotate_backward);
            fab22.startAnimation(fab_close);
            fab23.startAnimation(fab_close);
            fab22.setClickable(false);
            fab23.setClickable(false);
            isFabOpen = false;


        } else {

            fab21.startAnimation(rotate_forward);
            fab22.startAnimation(fab_open);
            fab23.startAnimation(fab_open);
            fab22.setClickable(true);
            fab23.setClickable(true);
            isFabOpen = true;


        }
    }





    public String POST(){
        InputStream inputStream = null;
        String result = "";
        try {

            Log.e("background","post");
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost("http://192.168.43.146:5000/hello");

            String json = "";

            // 3. build jsonObject
            //String movie="hello";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("movie",s);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }



    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String var="";
        @Override
        protected String doInBackground(String... urls) {

            Log.e("background","reached");

            var= POST();

            Log.e("background",var);

            return var;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

           // Toast.makeText(getBaseContext(),var, Toast.LENGTH_LONG).show();
            Intent as= new Intent(thirdpage.this,fourthpage.class);
            as.putExtra("mayank",var);
            startActivity(as);
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}

