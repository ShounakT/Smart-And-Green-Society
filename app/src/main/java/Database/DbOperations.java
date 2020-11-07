package Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class DbOperations {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();




    public void addNewUserAsSecretary(Map mapOfSecretary, String Uid){

        db.collection("Users")
                .document(Uid)
                .set(mapOfSecretary)
                .addOnSuccessListener(aVoid -> {
                    Log.d("SecretaryAdded","New Secretary Collection Added!");
                })
                .addOnFailureListener(e -> {
                    Log.d("SecretaryNotAdded","New Secretary Collection Not Added!");
                });
    }
    public void addNewUserAsResident(Map mapOfResident, String Uid){

        db.collection("Users")
                .document(Uid)
                .set(mapOfResident)
                .addOnSuccessListener(aVoid -> {
                    Log.d("UserAdded","New User Collection Added!");
                })
                .addOnFailureListener(e -> {
                    Log.d("UserNotAdded","New User Collection Not Added!");
                });

    }
    public void updateExistingUser(){

    }
}
