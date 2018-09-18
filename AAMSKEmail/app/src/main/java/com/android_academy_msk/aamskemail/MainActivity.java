package com.android_academy_msk.aamskemail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private Button previewButton;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.etEmail);
        previewButton = findViewById(R.id.bPreview);
        sendButton = findViewById(R.id.bSend);

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviewActivity();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String body = getString(R.string.body_text);
                String subject = getString(R.string.subject_text);
                String[] email;
                ArrayList<String> aaa = new ArrayList();
                aaa.add(emailText.getText().toString());
                email = aaa.toArray(new String[aaa.size()]);
                openEmailApp(body, email, subject);
            }
        });
    }

    public void openPreviewActivity() {
        Intent previewIntent = new Intent(this, PreviewActivity.class);
        previewIntent.putExtra("KEY_EMAIL", emailText.getText().toString());
        startActivity(previewIntent);
    }

    public void openEmailApp(String body, String[] email, String subject){
        Intent openEmailIntent = new Intent(Intent.ACTION_SENDTO);
        String mailToUri = "mailto:";
        openEmailIntent.setData(Uri.parse(mailToUri));
        openEmailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        openEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        openEmailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (openEmailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openEmailIntent);
        }
    }
}
