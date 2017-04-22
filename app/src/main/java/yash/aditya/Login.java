package yash.aditya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Button proceed_btn;
    EditText name,number;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        proceed_btn=(Button)findViewById(R.id.btnRegister);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        prefManager=new PrefManager(this);

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str=name.getText().toString();
                String number_str=number.getText().toString();
                prefManager.setName(name_str);
                prefManager.setNumber(number_str);
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}