package Authentication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.NoticeBoardActivity;
import com.example.smartandgreensociety.R;
import com.example.smartandgreensociety.UserProfileActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final int AuthUI_Req_Code = 47312;
    Button btnLoginRegister;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        btnLoginRegister = findViewById(R.id.btnLoginRegister);
        fAuth = FirebaseAuth.getInstance();

        checkUserLogin();
        btnLoginRegister.setOnClickListener(v -> {
            List<AuthUI.IdpConfig> provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());

            Intent intent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(provider).build();
            startActivityForResult(intent,AuthUI_Req_Code);
        });

    }

    private void checkUserLogin(){
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginRegisterActivity.this, HomeActivity.class));
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(requestCode == AuthUI_Req_Code){
            if(resultCode == RESULT_OK){
                FirebaseUser user = fAuth.getCurrentUser();
                Log.d("Login Confirm","User Logged In: " + user.getEmail());
                if(user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()){
                    Toast.makeText(getApplicationContext(),"Welcome New User! Please complete profile.",Toast.LENGTH_LONG)
                            .show();
                    //startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Welcome Back!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    this.finish();
                }

            }else{
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if(response == null){
                    Log.d("Login Error","User Cancelled");
                }else{
                    Log.e("Server Error",String.valueOf(response.getError()));
                }
            }
        }
    }
}