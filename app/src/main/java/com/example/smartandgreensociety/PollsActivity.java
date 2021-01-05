package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PollsActivity extends AppCompatActivity {

    FirestoreRecyclerAdapter adapter;
    Db db = new Db();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls2);

        db.test();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<Poll> option = db.getPollsRecyclerOptions();

        ((RecyclerView)findViewById(R.id.polls)).setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        adapter = new FirestoreRecyclerAdapter<Poll, PollsActivity.PollHolder>(option) {


            @Override
            public void onBindViewHolder(PollsActivity.PollHolder holder, int position, Poll model) {

                if (model == null) {
                    return;
                }


                holder.pollQues.setText(model.getQuestion());

                DocumentSnapshot snapshot = getSnapshots().getSnapshot(position);


                holder.parent_layout.setOnClickListener(View -> {

                    String docId = snapshot.getId();
                    Log.d("QA", "id: " + docId);

                    // Show Answers
                    // answers are in a list, each element a map
                    Intent intent = new Intent(PollsActivity.this, VoteForPollActivity.class);
                    Poll poll = snapshot.toObject(Poll.class);

                    poll.setId(snapshot.getId());

                    Globals.poll = poll;

                    startActivity(intent);

                });
            }

            @Override
            public PollsActivity.PollHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.pollholder, group, false);

                return new PollsActivity.PollHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        adapter.startListening();
        ((RecyclerView)findViewById(R.id.polls)).setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class PollHolder extends RecyclerView.ViewHolder {

        RelativeLayout parent_layout;
        TextView pollQues;


        public PollHolder(View itemView) {
            super(itemView);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            pollQues = itemView.findViewById(R.id.pollQues);

        }
    }
}