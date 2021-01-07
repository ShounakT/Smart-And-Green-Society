package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SocietyFeedbackActivity extends AppCompatActivity {

    FirestoreRecyclerAdapter adapter;
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        SocietyFeedbackActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<SocietyFeedback> socFeedback = db.getFeedbacksRecycler();

        ((RecyclerView)findViewById(R.id.feedbacks))
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        adapter = new FirestoreRecyclerAdapter<SocietyFeedback, SocietyFeedbackActivity.FeedbackHolder>(socFeedback) {


            @Override
            public SocietyFeedbackActivity.FeedbackHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.feedbackholder, group, false);

                return new SocietyFeedbackActivity.FeedbackHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }

            @Override
            protected void onBindViewHolder(@NonNull FeedbackHolder holder, int position, @NonNull SocietyFeedback model) {

                holder.tvFeedbackHeadings.setText(model.getFeedbackHeading());
                holder.tvFeedbackContents.setText(model.getFeedbackContent());

            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        ((RecyclerView)findViewById(R.id.feedbacks)).setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class FeedbackHolder extends RecyclerView.ViewHolder {

        TextView tvFeedbackHeadings, tvFeedbackContents;

        public FeedbackHolder(View itemView) {
            super(itemView);

            tvFeedbackHeadings = itemView.findViewById(R.id.tvFeedbackHeading);
            tvFeedbackContents = itemView.findViewById(R.id.tvFeedbackContent);

        }

    }
}