package in.paradigmshifters.olivercjoseph.mia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.paradigmshifters.olivercjoseph.Demo.CameraActivity;
import in.paradigmshifters.olivercjoseph.R;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private Button rtdbtn;
    private Button victoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        victoria=(Button)findViewById(R.id.rtdbtn1);

        mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MiMi");

        rtdbtn=(Button) findViewById(R.id.rtdbtn);

        rtdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent= new Intent(MainActivity.this, CameraActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });
        victoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent= new Intent(MainActivity.this, Bot.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendToStart();
        }
    }

    private void sendToStart() {
        Intent startIntent=new Intent(MainActivity.this,start_guide.class);
        startActivity(startIntent);
        finish();
    }


    //menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);



        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);


        if(item.getItemId()== R.id.main_logout_btn){
           FirebaseAuth.getInstance().signOut();
            Intent LogoutIntent=new Intent(MainActivity.this,StartActivity.class);
            startActivity(LogoutIntent);
            finish();


        }



        return true;
    }

}
