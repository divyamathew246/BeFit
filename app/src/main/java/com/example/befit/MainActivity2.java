package com.example.befit;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000 ;
    TextView mTextTv,t1,t2,t3,t4,t5;
    ImageButton mVoiceBtn;
    Button b,next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextTv = findViewById(R.id.textTv);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3= findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        b= findViewById(R.id.button);
        next =findViewById(R.id.button1);


        mVoiceBtn = findViewById(R.id.voiceBtn);

        // button click to show speech to text dialog


        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }
    private void speak() {
        // intent to show speech to text dialog

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something");

        //start intent
        try {
            //there was no error
            // show dialog
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        }
        catch(Exception e) {
            //if there was error
            //get message of error and show
            Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT).show();


        }

    }

    //recieve voice input and handle it


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null!=data) {
                    //get text array from voice input
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // set to text view
                    mTextTv.setText(result.get(0));
                    String listString = "";
                    for (String s : result)
                    {
                        listString += s.toLowerCase() + "\t";
                    }
                    String s1="diabetes";
                    String s2="thyroid";
                    String s3="cancer";
                    String s4="no disease";
                    String s5="no";
                    String s6="none";
                    String s7="high blood pressure";
                    String s8="Others";
                    int c=0;
                    if (listString.contains(s1))
                    {
                        t1.setText("Diabetes");
                        c=1;
                    }
                    if (listString.contains(s2))
                    {
                        t2.setText("Thyroid");
                        c=1;
                    }
                    if (listString.contains(s3))
                    {
                        t3.setText("Cancer");
                        c=1;
                    }
                    if (listString.contains(s4)||listString.contains(s5)||listString.contains(s6))
                    {
                        t4.setText("None");
                    }
                    if(listString.contains(s7))
                    {
                        if(c==1)
                        {
                            t5.setText("Others");
                        }
                    }
                }
                break;
            }
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                t1.setText(" ");
                t2.setText(" ");
                t3.setText(" ");
                t4.setText(" ");
                t5.setText(" ");
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity4.class);
                startActivity(intent);
            }
        });

    }
}