package com.example.smartandgreensociety.DatabaseOperations;

import android.content.Context;
import android.widget.Toast;

import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.PollingModule.Poll;
import com.example.smartandgreensociety.NoticeModule.Notice;
import com.example.smartandgreensociety.ComplaintModule.SocietyComplaint;
import com.example.smartandgreensociety.FeedbackModule.SocietyFeedback;
import com.example.smartandgreensociety.UserAuth.SP;
import com.example.smartandgreensociety.UserAuth.Society;
import com.example.smartandgreensociety.UserAuth.User;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {

    static final String pollsSubCollection = "Polls";
    static final String noticesSubCollection = "Notices";
    static final String complaintsSubCollection = "Complaints";
    static final String feedbacksSubCollection = "Feedbacks";


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
        firebaseUser = firebaseAuth.getCurrentUser();

        setUserDetailsGlobally(firebaseUser.getUid(),context);

        db
                .collection("Users")
                .document(firebaseUser.getUid())
                .set(newUserMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Registeration Successful!",Toast.LENGTH_SHORT).show();

                    }
                });


    }

    public void setUserDetailsGloballyWaitForCallback(String uId,Context context, final globalUserSet globalUserSet){
        db
                .collection("Users")
                .document(uId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                        Globals.user = new User();
                        Globals.user.setUid(uId);
                        Globals.user.setName(documentSnapshot.getString("name"));
                        Globals.user.setEmail(documentSnapshot.getString("email"));
                        Globals.user.setDesignation(documentSnapshot.getString("designation"));
                        Globals.user.setPhone(documentSnapshot.getString("phone"));
                        Globals.user.setSocietyRef(documentSnapshot.getString("societyRef"));

                        if (documentSnapshot.getString("societyRef") == null || documentSnapshot.getString("societyRef").equals("")){
                            globalUserSet.detailsSet();
                            return;
                        }

                        setSocietyDetailsGlobally(documentSnapshot.getString("societyRef"), new globalSocietySet() {
                            @Override
                            public void detailsSet() {
                                globalUserSet.detailsSet();
                            }
                        });


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

                        Globals.user = new User();
                        Globals.user.setUid(uId);
                        Globals.user.setName(documentSnapshot.getString("name"));
                        Globals.user.setEmail(documentSnapshot.getString("email"));
                        Globals.user.setDesignation(documentSnapshot.getString("designation"));
                        Globals.user.setPhone(documentSnapshot.getString("phone"));
                        Globals.user.setSocietyRef(documentSnapshot.getString("societyRef"));
                    }
                });
    }
    public void setSocietyDetailsGlobally(String sId, final globalSocietySet globalSocietySet){

        db
                .collection("Societies")
                .document(sId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Globals.society = new Society();
                        Globals.society.setSocietyRef(sId);
                        Globals.society.setSocietyName(documentSnapshot.getString("societyName"));


                        globalSocietySet.detailsSet();
                    }
                });
    }

    public void createNewSociety(Map newSocietyMap,Context context){

        DocumentReference documentReference =
        db
                .collection("Societies")
                .document();

        Globals.society = new Society();
        Globals.society.setSocietyRef(documentReference.getId());
        Globals.society.setSocietyName(newSocietyMap.get("societyName").toString());

        documentReference
                            .set(newSocietyMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    addMemberInSociety(documentReference,context);
//                                    updateSocietyDetails(documentReference);
                                }
                            });

    }

    private void updateSocietyDetails(DocumentReference documentReference) {
        Map societyMap = Globals.society.toMapSociety();

        documentReference

                .update(societyMap);
    }

    public void addMemberInSociety(DocumentReference sId,Context context){

        Globals.user.setSocietyRef(sId.getId());
        updateUserDetails();
        sId
                .collection("Members")
                .add(Globals.user.toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {


                    }
                });
    }

    private void updateUserDetails() {

        Map userMap = Globals.user.toMap();

        db
                .collection("Users")
                .document(firebaseUser.getUid())
                .update(userMap);
    }

    public void addResident(String emailId){


        db
                .collection("Users")
                .whereEqualTo("email",emailId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        DocumentSnapshot userDocSnap = queryDocumentSnapshots.getDocuments().get(0);
                        User user=userDocSnap.toObject(User.class);
                        userDocSnap.getReference().update("societyRef",Globals.society.getSocietyRef());
                        Map userMap = user.toMap();
                        addOtherMemberInSociety(userMap);
                    }
                });
    }

    private void addOtherMemberInSociety(Map residentMap) {

        DocumentReference documentReference =
        db
                .collection("Societies")
                .document(Globals.society.getSocietyRef());

        documentReference
                .collection("Members")
                .add(residentMap);
    }

    public void createPoll(String ques, List<String> options){
        HashMap<String,Integer> pollCounts = new HashMap<>();

        for (String opt : options){
            pollCounts.put(opt,0);
        }

        HashMap<String,Object> poll = new HashMap<>();
        poll.put("question",ques);
        poll.put("options",pollCounts);

        db.collection("Societies").document(Globals.society.getSocietyRef())
                .collection(pollsSubCollection)
                .add(poll);
    }

    public void addNotice(Map noticeMap){

        db.collection("Societies").document(Globals.society.getSocietyRef())
                .collection(noticesSubCollection)
                .add(noticeMap);

    }
    public void addComplaint(Map complaintMap){

        db.collection("Societies").document(Globals.society.getSocietyRef())
                .collection(complaintsSubCollection)
                .add(complaintMap);

    }
    public void addFeedback(Map feedbackMap){

        db.collection("Societies").document(Globals.society.getSocietyRef())
                .collection(feedbacksSubCollection)
                .add(feedbackMap);

    }


    public void voteInPoll(String pollId, String option){
        db.collection("Societies").document(Globals.user.getSocietyRef())
                .collection(pollsSubCollection)
                .document(pollId)
                .update("options."+option, FieldValue.increment(1));
    }

    public FirestoreRecyclerOptions<Poll> getPollsRecyclerOptions(){
        return new FirestoreRecyclerOptions.Builder<Poll>()
                    .setQuery(db.collection("Societies").document(Globals.society.getSocietyRef()).collection("Polls"), Poll.class)
                    .build();
    }

    public FirestoreRecyclerOptions<Notice> getNoticeRecycler(){
        return new FirestoreRecyclerOptions.Builder<Notice>()
                .setQuery(db.collection("Societies").document(Globals.society.getSocietyRef()).collection("Notices"),Notice.class)
                .build();
    }

    public FirestoreRecyclerOptions<SocietyComplaint> getComplaintsRecycler(){
        return new FirestoreRecyclerOptions.Builder<SocietyComplaint>()
                .setQuery(db.collection("Societies").document(Globals.society.getSocietyRef()).collection(complaintsSubCollection),SocietyComplaint.class)
                .build();
    }

    public FirestoreRecyclerOptions<SocietyFeedback> getFeedbacksRecycler(){
        return new FirestoreRecyclerOptions.Builder<SocietyFeedback>()
                .setQuery(db.collection("Societies").document(Globals.society.getSocietyRef()).collection(feedbacksSubCollection),SocietyFeedback.class)
                .build();
    }

    public interface globalUserSet {
        void detailsSet();
    }

    public interface globalSocietySet {
        void detailsSet();
    }

}
