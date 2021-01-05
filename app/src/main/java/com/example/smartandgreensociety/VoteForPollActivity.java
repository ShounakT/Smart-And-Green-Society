package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;

public class VoteForPollActivity extends AppCompatActivity {

    Db db  = new Db();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_for_poll);

        Poll poll = Globals.poll;

        ((TextView)findViewById(R.id.ques)).setText(poll.getQuestion());

        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.voteInPoll(poll.getId(),"Yes");
            }
        });

        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.voteInPoll(poll.getId(),"No");
            }
        });
    }
}