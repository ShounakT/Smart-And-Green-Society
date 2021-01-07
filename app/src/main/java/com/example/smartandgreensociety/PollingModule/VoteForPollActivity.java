package com.example.smartandgreensociety.PollingModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

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
                startActivity(new Intent(VoteForPollActivity.this, HomeActivity.class));
                VoteForPollActivity.this.finish();
            }
        });

        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.voteInPoll(poll.getId(),"No");
                startActivity(new Intent(VoteForPollActivity.this, HomeActivity.class));
                VoteForPollActivity.this.finish();
            }
        });
    }
}