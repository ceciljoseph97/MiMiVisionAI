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

import java.util.Objects;

import in.paradigmshifters.olivercjoseph.R;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mUsername;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button mRegisterBtn;

    private Toolbar mToolbar;

    //progress Dialog

    private ProgressDialog mRegProgress;//deprecated

    //firbase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Toolbar set

        mToolbar=(Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //progressdialog

        mRegProgress=new ProgressDialog(this);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        //Fieldset
        mUsername=(TextInputLayout) findViewById(R.id.reg_user_name);
        mEmail=(TextInputLayout) findViewById(R.id.reg_email);
        mPassword=(TextInputLayout) findViewById(R.id.reg_password);
        mRegisterBtn = (Button) findViewById(R.id.reg_create_btn);

//button function
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name= Objects.requireNonNull(mUsername.getEditText()).getText().toString();
                String email=mEmail.getEditText().getText().toString();
                String password=mPassword.getEditText().getText().toString();
                if(!TextUtils.isEmpty(user_name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){
                    mRegProgress.setTitle("Registering User....");
                    mRegProgress.setMessage("Please wait while we create your account..!");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    Register_user(user_name,email,password);
                }
            }


        });

    }
//function definition
    private void Register_user(String user_name, String email, String password) {

       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                mRegProgress.dismiss();
                Intent mainIntent= new Intent(RegisterActivity.this,MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

               }else {
                   mRegProgress.hide();
                   Toast.makeText(RegisterActivity.this,"Authentication failed, Please check the form and try again.", Toast.LENGTH_LONG).show();
               }
           }
       });

    }
}
