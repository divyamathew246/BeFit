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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class MainActivity3 extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ImageButton mVoiceBtn;
    TextView mTextTv, t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mTextTv = findViewById(R.id.textTv);
        t1 = findViewById(R.id.t1);


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
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        //start intent
        try {
            //there was no error
            // show dialog
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        } catch (Exception e) {
            //if there was error
            //get message of error and show
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();


        }

    }

    //recieve voice input and handle it


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    //get text array from voice input
                    String dd = "", yy = "", mm = "";
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // set to text view
                    mTextTv.setText(result.get(0));
                    String listString = "";


                    for (String s : result) {
                        listString += s.toLowerCase() + "\t";
                    }

                    if (listString.contains("january")) {
                        mm = "01";
                    } else if (listString.contains("february")) {
                        mm = "02";
                    } else if (listString.contains("march")) {
                        mm = "03";
                    } else if (listString.contains("april")) {
                        mm = "04";
                    } else if (listString.contains("may")) {
                        mm = "05";
                    } else if (listString.contains("june")) {
                        mm = "06";
                    } else if (listString.contains("july")) {
                        mm = "07";
                    } else if (listString.contains("august")) {
                        mm = "08";
                    } else if (listString.contains("september")) {
                        mm = "09";
                    } else if (listString.contains("october")) {
                        mm = "10";
                    } else if (listString.contains("november")) {
                        mm = "11";
                    } else if (listString.contains("december")) {
                        mm = "12";
                    }

                    String number = listString.replaceAll("\\D+", " ");

                    String arr1[] = number.split(" ", 2);
                    String firstWord = arr1[0];
                    String theRest = arr1[1];
                    int i = Integer.parseInt(arr1[0]);


                    if(i>1000)
                    {
                        yy=arr1[0];
                        String arr2[] = theRest.split(" ", 2);
                        mm=arr2[0];
                        dd=arr2[1];
                        t1.setText(dd + "/" + mm + "/" + yy);
                    }




                    if (i < 31 && mm != " ") {
                        dd = arr1[0];
                        yy = arr1[1];
                        t1.setText(dd + "/" + mm + "/" + yy);
                    }


                    if (i < 31 && mm == "") {
                        dd = arr1[0];
                        String arr2[] = theRest.split(" ", 2);
                        //int i2 = Integer.parseInt(arr2[0]);
                        mm = arr2[0];
                        yy = arr2[1];
                        t1.setText(dd+"/"+mm+"/"+yy);
                    }


                }
                break;
            }
        }
    }
}