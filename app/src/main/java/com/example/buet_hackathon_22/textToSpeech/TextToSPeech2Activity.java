package com.example.buet_hackathon_22.textToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buet_hackathon_22.R;

import java.util.ArrayList;
import java.util.Locale;

public class TextToSPeech2Activity extends AppCompatActivity {
    private TextView textView;
    private ImageButton imageButton;
    public static final int REQUEST_CODE_SPEECH_INPUT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech2);
        textView=findViewById(R.id.textviewinTextToSpeech);
        imageButton=findViewById(R.id.voiceButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech();
            }
        });
    }

    private void speech() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speech something");
        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }catch(Exception e){
            Toast.makeText(TextToSPeech2Activity.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:
            {
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                }
                break;
            }
        }
    }
}