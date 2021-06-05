package com.example.befit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000 ;
    TextView mTextTv,t1,t2,t3,t4;
    ImageButton mVoiceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextTv = findViewById(R.id.textTv);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3= findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);

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
                    //String a[]={};
                    //ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(a));

                    String s1="diabetes";
                    String s2="thyroid";
                    String s3="cancer";
                    String s4="no disease";

                    if (result.contains(s1))
                    {
                        //System.out.println(s1);
                        t1.setText(s1);
                    }
                    if (result.contains(s2))
                    {
                        // System.out.println(s2);
                        t2.setText(s2);
                    }
                    if (result.contains(s3))
                    {
                        //System.out.println(s3);
                        t3.setText(s3);
                    }
                    if (result.contains(s4))
                    {
                        //System.out.println(s3);
                        t4.setText(s4);
                    }


                }
                break;
            }
        }

    }
}