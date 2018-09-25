package com.android_academy_msk.businesscard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private ImageButton buttonTelegram;
    private ImageButton buttonInstagram;
    private ImageButton buttonTwitter;
    private EditText etMessage;

    private static final int TELEGRAM = 1;
    private static final int INSTAGRAM = 2;
    private static final int TWITTER = 3;
    private static final String NO_URL = "url not found";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.bSend);
        buttonTelegram = findViewById(R.id.ibTelegram);
        buttonInstagram = findViewById(R.id.ibInstagram);
        buttonTwitter = findViewById(R.id.ibTwitter);
        etMessage = findViewById(R.id.etMessage);

        buttonTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocial(TELEGRAM);
            }
        });

        buttonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocial(INSTAGRAM);
            }
        });

        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocial(TWITTER);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = etMessage.getText().toString();
                String subject = getString(R.string.subject_text);
                String[] email;
                ArrayList<String> aaa = new ArrayList();
                aaa.add(getString(R.string.my_email));
                email = aaa.toArray(new String[aaa.size()]);
                sendEmail(body, email, subject);
            }
        });
    }

    public void openSocial(int type) {
        String url;
        switch (type) {
            case TELEGRAM:
                url = getString(R.string.telegram_url);
                break;
            case INSTAGRAM:
                url = getString(R.string.instagram_url);
                break;
            case TWITTER:
                url = getString(R.string.twitter_url);
                break;
            default:
                url = NO_URL;
        }
        if (url != NO_URL) {
            Uri page = Uri.parse(url);
            Intent telegramIntent = new Intent(Intent.ACTION_VIEW, page);
            if (telegramIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(telegramIntent);
            }
        }
    }

    public void sendEmail(String body, String[] email, String subject) {
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
