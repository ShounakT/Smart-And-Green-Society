package Database;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DbOperations {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addNewUserAsSecretary(Map mapOfSecretary){
        String nameOfSecretary = mapOfSecretary.get("name").toString();
        db.collection("Users")
                .document(nameOfSecretary)
                .set(mapOfSecretary)
                .addOnSuccessListener(aVoid -> {
                    Log.d("SecretaryAdded","Collection not created");
                })
                .addOnFailureListener(e -> {
                    Log.d("SecretaryNotAdded","Collection Created");
                });
    }
    public void addNewUserAsResident(){

    }
    public void updateExistingUser(){

    }
}
