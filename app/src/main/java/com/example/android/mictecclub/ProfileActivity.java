package com.example.android.mictecclub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mictecclub.Data.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account";

    private TextView profileName, profileEmail;
    private ImageView profileImage;
    private Button signOut;

    private GoogleSignInClient googleSignInClient;

    private static final String TAG = "AndroidClarified";
    private User user;
    private SharedPreferences prefs;
    public static final String MyPREFERENCES = "com.example.android.mictecclub.PREFERENCE_FILE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileImage = findViewById(R.id.profile_image);

        setDataOnView();
        signOut = (Button) findViewById(R.id.sign_out);
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*
//              Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
//              listener which will be invoked once the sign out is the successful
//               */
//                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        //On Succesfull signout we navigate the user back to LoginActivity
//                        Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                });
//            }
//        });
    }

    private void setDataOnView() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);

        Picasso.with(this).load(googleSignInAccount.getPhotoUrl()).transform(new CircleTransform()).into(profileImage);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());

        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("imageUri", String.valueOf(googleSignInAccount.getPhotoUrl()));
        editor.putString("userName",googleSignInAccount.getDisplayName());
        editor.putString("userEmail",googleSignInAccount.getEmail());
        editor.apply();

        user=new User(googleSignInAccount.getPhotoUrl(),googleSignInAccount.getDisplayName(),googleSignInAccount.getEmail());

        Toast.makeText(this, ""+user, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(MainActivity.GOOGLE_ACCOUNT, getIntent().getParcelableExtra(GOOGLE_ACCOUNT));

        startActivity(intent);
//        finish();
    }
}
