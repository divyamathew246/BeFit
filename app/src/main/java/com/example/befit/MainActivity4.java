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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity4 extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000 ;
    ImageButton mVoiceBtn;
    TextView mTextTv,t1;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mTextTv = findViewById(R.id.textTv);
        t1 = findViewById(R.id.t1);
        next = findViewById(R.id.button2);



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

                    String number = listString.replaceAll("\\D+","");
                    int i=Integer.parseInt(number);
                    if(i<100)
                    {
                        i=i*100000;
                    }

                    if(i%500000<=200000) {
                        if (i < 200000) {
                            t1.setText("income < 2 lakhs");
                        } else if (i >= 200000 && i  < 500000) {
                            t1.setText("2 - 5 lakhs");
                        } else if (i >= 500000 && i < 1000000) {
                            t1.setText("5 - 10 lakhs");
                        } else if (i >= 1000000) {
                            t1.setText("10 lakhs <= income");
                        }
                    }
                    else
                    {     if (i < 500000) {
                        t1.setText("income < 5 lakhs");
                    } else if (i >= 500000 && i < 1500000) {
                        t1.setText("5 - 15 lakhs");
                    } else if (i >= 1500000 && i < 2000000) {
                        t1.setText("15 - 20 lakhs");
                    } else if (i >= 2000000) {
                        t1.setText("20 lakhs <= income");
                    }
                    }
                }
                break;
            }
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }
}