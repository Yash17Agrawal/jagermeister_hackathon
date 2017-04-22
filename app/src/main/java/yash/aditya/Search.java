package yash.aditya;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Search extends AppCompatActivity {


   static EditText issue,category,to_whom;
    Button post_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        issue=(EditText)findViewById(R.id.issue);
        category=(EditText)findViewById(R.id.category);
        to_whom=(EditText)findViewById(R.id.to_whom);


        post_btn=(Button)findViewById(R.id.post_btn);

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String issue_str=issue.getText().toString();
                String category_str=category.getText().toString();
                String to_whom_str=to_whom.getText().toString();

                new HttpAsyncTask().execute();



            }
        });

    }


    public static String POST(){
        InputStream inputStream = null;
        String result = "";
        try {

            Log.e("tag","reached post");

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost("http://192.168.43.58:5000/yash");

            String json = "";

            // 3. build jsonObject
//            String movie=movie_name.getText().toString();
            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("movie", movie);


            // 4. convert JSONObject to JSON to String
           // json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            String issue_str=issue.getText().toString();
            String category_str=category.getText().toString();
            String to_whom_str=to_whom.getText().toString();

            String req="issue="+issue_str+" $"+"Category="+category_str+" $"+"to_whom="+to_whom_str;
            StringEntity se = new StringEntity(req);

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

        Log.e("tag", result);
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String var="";
        @Override
        protected String doInBackground(String... urls) {


            Log.e("http","reached async");

            var= POST();
            return var;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
          //  make_UI_changes(var);
            Toast.makeText(getBaseContext(), var, Toast.LENGTH_LONG).show();
//
            Intent i=new Intent(Search.this,FinalFeed.class);
            i.putExtra("yo",var);
            startActivity(i);
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
