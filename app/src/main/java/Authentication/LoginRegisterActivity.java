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
import com.firebase.ui.auth.AuthMethodPickerLayout;
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

        List<AuthUI.IdpConfig> provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());

        AuthMethodPickerLayout authMethodPickerLayout = new AuthMethodPickerLayout
                .Builder(R.layout.activity_login_register)
                .setEmailButtonId(R.id.btnLoginRegister)
                .build();
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAuthMethodPickerLayout(authMethodPickerLayout)
                .setAvailableProviders(provider).setIsSmartLockEnabled(false).build(),AuthUI_Req_Code);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(requestCode == AuthUI_Req_Code){
            if(resultCode == RESULT_OK){

                FirebaseUser user = fAuth.getCurrentUser();

                if(user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()){
                    Toast.makeText(getApplicationContext(),"Welcome New User! Please complete profile.",Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    intent.putExtra("Uid",user.getUid());
                    startActivity(intent);
                    this.finish();
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
                    Log.d("Server Error",String.valueOf(response.getError()));
                }
            }
        }
    }
}