package com.androidTest.androidtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private TextView tvDetailTitle, tvDetailArtist;
    private Button btnVisitWebsite, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailArtist = findViewById(R.id.tvDetailArtist);
        btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        btnBack = findViewById(R.id.btnBack);

        // استلام البيانات اللي جت من الشاشة الرئيسية
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String artistDetails = intent.getStringExtra("artistDetails");

            tvDetailTitle.setText(title);
            tvDetailArtist.setText(artistDetails);
        }

        // فتح رابط خارجي بالمتصفح
        btnVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com";
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(webIntent);
            }
        });

        // رجوع للشاشة الأولى
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}