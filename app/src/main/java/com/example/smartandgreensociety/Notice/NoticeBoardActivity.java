package com.example.smartandgreensociety.Notice;

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

import com.example.smartandgreensociety.Database.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class NoticeBoardActivity extends AppCompatActivity {

    FirestoreRecyclerAdapter adapter;
    DiscreteScrollView discreteScrollView;
    Db db = new Db();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        discreteScrollView = findViewById(R.id.notices);
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        NoticeBoardActivity.this.finish();
        return super.onSupportNavigateUp();
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<Notice> notice = db.getNoticeRecycler();

        /*((RecyclerView)findViewById(R.id.notices))
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
*/

        adapter = new FirestoreRecyclerAdapter<Notice, NoticeBoardActivity.NoticeHolder>(notice) {

            @Override
            protected void onBindViewHolder(@NonNull NoticeHolder holder, int position, @NonNull Notice model) {

                holder.tvNoticeHeading.setText(model.getNoticeTitle());
                holder.tvNoticeContents.setText(model.getNoticeContent());

            }
            @Override
            public NoticeBoardActivity.NoticeHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.noticeholder, group, false);

                return new NoticeBoardActivity.NoticeHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }


        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        ((DiscreteScrollView)findViewById(R.id.notices)).setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class NoticeHolder extends RecyclerView.ViewHolder {

        TextView tvNoticeHeading, tvNoticeContents;

        public NoticeHolder(View itemView) {
            super(itemView);
            tvNoticeHeading = itemView.findViewById(R.id.tvNoticeHeading);
            tvNoticeContents = itemView.findViewById(R.id.tvNoticeContents);

        }

    }
}