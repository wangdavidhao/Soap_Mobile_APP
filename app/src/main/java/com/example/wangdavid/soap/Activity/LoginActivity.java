package com.example.wangdavid.soap.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangdavid.soap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText editTextUser;
    EditText editTextMdp;
    Button signUpButton;
    Button buttonConnexion;


    String mail;
    String password;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText)findViewById(R.id.editTextUser);
        editTextMdp = (EditText)findViewById(R.id.editTextMdp);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        buttonConnexion = (Button)findViewById(R.id.buttonConnexion);

        auth= FirebaseAuth.getInstance();

        auth.signOut();




        ///LOGIN FOR SPECIFIC USER
        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = editTextUser.getText().toString();
                password = editTextMdp.getText().toString();

                if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password))
                {
                    auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e("TAG", "User ID : " + auth.getCurrentUser().getUid());
                                Intent sendToMain = new Intent (LoginActivity.this, MainActivity.class);
                                startActivity(sendToMain);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                    });

                }
            }
        });



    }
}
