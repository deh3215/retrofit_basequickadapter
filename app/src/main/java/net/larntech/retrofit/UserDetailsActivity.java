package net.larntech.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class UserDetailsActivity extends AppCompatActivity {

    TextView username,email,joined;
    UserResponse userResponse;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        username  = findViewById(R.id.username);
        email  = findViewById(R.id.email);
        joined  = findViewById(R.id.joined);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            userResponse = (UserResponse) intent.getSerializableExtra("data");

            String usernamedata = userResponse.getUsername();
            String useremail = userResponse.getEmail();
            String userjoined = userResponse.getDate_joined();


            username.setText(usernamedata);
            email.setText(useremail);
            joined.setText(userjoined);


        }


    }
}
