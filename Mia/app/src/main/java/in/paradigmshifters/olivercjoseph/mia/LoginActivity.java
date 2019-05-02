package in.paradigmshifters.olivercjoseph.mia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import in.paradigmshifters.olivercjoseph.R;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextInputLayout mloginEmail;
    private TextInputLayout mloginPassword;
    private Button mLoginBtn;
    //progress Dialog

    private ProgressDialog mLoginProgress;//deprecated
    //auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        //toolbar
        mToolbar=(Toolbar) findViewById(R.id.login_layout_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //ProgressDialog
        mLoginProgress=new ProgressDialog(this);



        //setting fields
        mloginEmail=(TextInputLayout) findViewById(R.id.login_email1);
        mloginPassword=(TextInputLayout) findViewById(R.id.login_password1);
        mLoginBtn = (Button) findViewById(R.id.login_main_btn);

        //Button Funcction definition
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mloginEmail.getEditText().getText().toString();
                String password=mloginPassword.getEditText().getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)) {
                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    loginUser(email,password);
                    

                }

            }
        });


    }

    private void loginUser(String email, String password) {


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    mLoginProgress.dismiss();
                    Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();

                }
                else {
                    mLoginProgress.hide();
                    Toast.makeText(LoginActivity.this,"Cannot sign in , Please check the form and try again.", Toast.LENGTH_LONG).show();


                }
            }
        });

    }

}
