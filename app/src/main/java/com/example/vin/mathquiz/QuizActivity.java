package com.example.vin.mathquiz;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    public int number = 19;
    public int score = 0;
    public int qCount = 4;
    public boolean trueBut = false;
    public boolean falseBut = false;
    public boolean temp_Prime;
    public int flag=1;

    private GoogleApiClient client;

    boolean isPrime(int num,boolean trueBut,boolean falseBut) {
        mNextButton = (Button) findViewById(R.id.next_button);
        for(int i = 2; i < num; i++){
            if(num % i == 0)
            {  // TextView myText = (TextView) findViewById(R.id.textView2);
               // String myString2 =  String.valueOf(number) + " not prime";
               // myText.setText(myString2);
                if(falseBut == true)
                {Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    score++;
                    mNextButton.callOnClick();

                    trueBut = false;
                    falseBut = false;
                    return false;}
                if(trueBut == true)
                {Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    mNextButton.callOnClick();
                    trueBut = false;
                    falseBut = false;
                    return false;}
                return false;

            }
        }
       // TextView myText = (TextView) findViewById(R.id.textView2);
        //String myString1 = String.valueOf(number)+ "prime";
       // myText.setText(myString1);
        if(trueBut == true)
        {Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score++;
            mNextButton.callOnClick();
            trueBut = false;
            falseBut = false;
            return true;}
        if(falseBut == true)
        {Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            mNextButton.callOnClick();
            trueBut = false;
            falseBut = false;
            return true;}
        trueBut = false;
        falseBut = false;
        return true;
    }

    public void generate(View view) {
        if(qCount > 0) {
            Random rand = new Random();
            number = rand.nextInt(50) + 1;
            TextView myText1 = (TextView) findViewById(R.id.textView);
            String myString = "Is " + String.valueOf(number) + " a prime number ?";
            myText1.setText(myString);
            TextView myText2 = (TextView) findViewById(R.id.textView3);
            String myString3 = "Score : " + String.valueOf(score);
            myText2.setText(myString3);
            qCount--;
        }
        else
        {   mFalseButton.setVisibility(view.INVISIBLE);
            mTrueButton.setVisibility(view.INVISIBLE);
            mNextButton.setVisibility(view.INVISIBLE);
            TextView myText3 = (TextView) findViewById(R.id.textView);
            String myString = "WELL-PLAYED!!!!";
            myText3.setText(myString);
            TextView myText2 = (TextView) findViewById(R.id.textView3);
            String myString3 = "Score : " + String.valueOf(score);
            myText2.setText(myString3);}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

            mTrueButton = (Button) findViewById(R.id.true_button);
            mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {temp_Prime = isPrime(number,true,false);}
            });


            mFalseButton = (Button) findViewById(R.id.false_button);
            mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {temp_Prime = isPrime(number,false,true);}
            });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();


        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.vin.mathquiz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.vin.mathquiz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
