package com.example.smartandgreensociety.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.smartandgreensociety.Authentication.User;
import com.example.smartandgreensociety.Globals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class DbOperations {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    // Auth
    public void userExists(String uid, final onUserExistsCallback onUserExistsCallback) {
        db
                .collection("Users")
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Log.e("User", documentSnapshot.getData().toString());
                        Globals.USER = documentSnapshot.toObject(User.class);
                        Log.e("User", "Globals Set");
                        Globals.USER.setUid(uid);
                        if (onUserExistsCallback != null) {
                            onUserExistsCallback.userExists(true);
                        }
                    } else {
                        if (onUserExistsCallback != null) {
                            onUserExistsCallback.userExists(false);
                        }
                    }
                });
    }

    // Auth
    public interface onUserExistsCallback {
        void userExists(boolean userExists);
    }


    public void setExistingUser(String uId){
        Log.e("Check","Inside Dboperartions setexitinguser");
        Log.d("uid inside dboperations",uId);
        db
                .collection("Users")
                .document(uId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot.exists()){
                            Log.e("User", documentSnapshot.getData().toString());
                            Globals.USER = documentSnapshot.toObject(User.class);
                            Globals.USER.setUid(uId);
                        }
                    }
                });
    }
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


    public void updateExistingResident(Map toMap,String Uid){
        Log.e("update","Inside dboperations update function");
        db
                .collection("Users")
                .document(Uid)
                .set(toMap)
                .addOnSuccessListener(o -> {
                    Log.d("UserUpdated","New User Collection Updated!");
                })
                .addOnFailureListener(e -> {
                    Log.d("UserNotUpdated","New User Collection Not Updated!");
                });
    }

    public void updateExistingSecretary(Map toMap,String Uid){
        Log.e("update","Inside dboperations update function");
        db
                .collection("Users")
                .document(Uid)
                .set(toMap)
                .addOnSuccessListener(o -> {
                    Log.d("UserUpdated","New User Collection Updated!");
                })
                .addOnFailureListener(e -> {
                    Log.d("UserNotUpdated","New User Collection Not Updated!");
                });
    }
}
