package com.example.smartandgreensociety.DatabaseOperations;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.UserAuth.SP;
import com.example.smartandgreensociety.UserAuth.Society;
import com.example.smartandgreensociety.UserAuth.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Db {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;

    public interface NewUser{

        void isNewUserCallback(boolean isNewUser);

    }

    public void isNewUserCallback(String uId, final NewUser newUser ){

        db
                .collection("Users")
                .document(uId)
                .get()
                .addOnCompleteListener(task -> {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()){
                        if(newUser != null) {
                            newUser.isNewUserCallback(false);
                        }

                    }else{

                        if(newUser != null) {
                            newUser.isNewUserCallback(true);
                        }
                    }
                });
    }

    public void createNewUser(Map newUserMap, Context context){

        SP.setSP(context,"name",newUserMap.get("name").toString());
        SP.setSP(context,"email",newUserMap.get("email").toString());
        SP.setSP(context,"designation",newUserMap.get("designation").toString());
        SP.setSP(context,"phone",newUserMap.get("phone").toString());
        SP.setSP(context,"societyRef",newUserMap.get("societyRef").toString());

        firebaseUser = firebaseAuth.getCurrentUser();
        db
                .collection("Users")
                .document(firebaseUser.getUid())
                .set(newUserMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Registeration Successful!",Toast.LENGTH_SHORT).show();
                        setUserDetailsGlobally(firebaseUser.getUid(),context);
                    }
                });


    }

    public void setUserDetailsGlobally(String uId,Context context){

        db
                .collection("Users")
                .document(uId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        SP.setSP(context,"name",documentSnapshot.getString("name"));
                        SP.setSP(context,"email",documentSnapshot.getString("email"));
                        SP.setSP(context,"designation",documentSnapshot.getString("designation"));
                        SP.setSP(context,"phone",documentSnapshot.getString("phone"));
                        SP.setSP(context,"societyRef",documentSnapshot.getString("societyRef"));
                        Globals.user = new User();
                        Globals.user.setUid(uId);
                        Globals.user.setName(documentSnapshot.getString("name"));
                        Globals.user.setEmail(documentSnapshot.getString("email"));
                        Globals.user.setDesignation(documentSnapshot.getString("designation"));
                        Globals.user.setPhone(documentSnapshot.getString("phone"));
                        Globals.user.setSocietyRef(documentSnapshot.getString("societyRef"));
                        Log.d("S",documentSnapshot.getString("name"));
                    }
                });
    }
    public void setSocietyDetailsGlobally(String sId){

        db
                .collection("Society")
                .document(sId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Globals.society = new Society();
                        Globals.society.setSocietyRef(sId);
                        Globals.society.setSocietyName(documentSnapshot.getString("societyName"));
                    }
                });
    }

    public void createNewSociety(Map newSocietyMap,Context context){

        DocumentReference documentReference =
        db
                .collection("Societies")
                .document();

        documentReference
                            .set(newSocietyMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    setSocietyDetailsGlobally(documentReference.getId());
                                    addMemberInSociety(documentReference);
                                }
                            });

    }

    public void addMemberInSociety(DocumentReference sId){


        sId
                .collection("Members")
                .add(Globals.user.toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Globals.user.setSocietyRef(sId.getId());
                    }
                });

    }



}
