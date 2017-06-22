package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.resslen.projektbdio.R.id.etRepeatPassword;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etSurname = (EditText) findViewById(R.id.etSurname);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final EditText etRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String name = etName.getText().toString();
                final String surname = etSurname.getText().toString();
                final String password = etPassword.getText().toString();
                final String repeat_password = etRepeatPassword.getText().toString();
                final String email = etEmail.getText().toString();
                final String state = "1";

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success && (password.equals(repeat_password))){
                                Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                                RegisterUserActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
                                builder.setMessage("Rejestracja niepomyślna. Sprawdź poprawność wprowadzonych danych")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(surname, name, password, email, state, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterUserActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}
