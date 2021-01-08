package com.example.smartandgreensociety.ComplaintModule;

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
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SocietyComplaintActivity extends AppCompatActivity {

    FirestoreRecyclerAdapter adapter;
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        SocietyComplaintActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_complaint);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<SocietyComplaint> socComplaint = db.getComplaintsRecycler();

        ((RecyclerView)findViewById(R.id.complaints))
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        adapter = new FirestoreRecyclerAdapter<SocietyComplaint, SocietyComplaintActivity.ComplaintHolder>(socComplaint) {

            @Override
            protected void onBindViewHolder(@NonNull ComplaintHolder holder, int position, @NonNull SocietyComplaint model) {
                Log.e("SOC", model.getComplaintHeading());
                holder.tvComplaintHeadings.setText(model.getComplaintHeading());
                holder.tvComplaintContents.setText(model.getComplaintContent());

            }
            @Override
            public SocietyComplaintActivity.ComplaintHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.complaintholder, group, false);

                return new SocietyComplaintActivity.ComplaintHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }




        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        ((RecyclerView)findViewById(R.id.complaints)).setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class ComplaintHolder extends RecyclerView.ViewHolder {

        TextView tvComplaintHeadings, tvComplaintContents;

        public ComplaintHolder(View itemView) {
            super(itemView);

            tvComplaintHeadings = itemView.findViewById(R.id.tvComplaintHeading);
            tvComplaintContents = itemView.findViewById(R.id.tvComplaintContent);

        }

    }
}