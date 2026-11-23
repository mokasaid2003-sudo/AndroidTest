package com.androidTest.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter, tvTitle, tvArtistDetails;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    // الصور حسب الترتيب بالملف
    private int[] images = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    // عناوين اللوحات المطابقة للصور
    private String[] titles = {
            "Starry Night",
            "Girl with a Pearl Earring",
            "Mona Lisa",
            "Las Meninas",
            "Irises",
            "Lady with an Ermine"
    };

    // الفنانين وسنة الرسم
    private String[] artists = {
            "Vincent van Gogh (1889)",
            "Johannes Vermeer (1665)",
            "Leonardo da Vinci (1503)",
            "Diego Velazquez (1656)",
            "Vincent van Gogh (1889)",
            "Leonardo da Vinci (1489)"
    };

    private int currentIndex = 0; // اللوحة الحالية

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistDetails = findViewById(R.id.tvArtistDetails);
        ivArtwork = findViewById(R.id.ivArtwork);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // استرجاع رقم اللوحة لو الشاشة دارت
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0);
        }

        updateArtworkDisplay();

        // الانتقال للوحة التالية
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < images.length - 1) {
                    currentIndex++;
                    updateArtworkDisplay();
                }
            }
        });

        // الرجوع للوحة السابقة
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateArtworkDisplay();
                }
            }
        });

        // فتح شاشة التفاصيل عند الضغط على الصورة
        ivArtwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
                intent.putExtra("title", titles[currentIndex]);
                intent.putExtra("artistDetails", artists[currentIndex]);
                startActivity(intent);
            }
        });
    }

    // تحديث الصورة والنصوص والعداد وحالة الأزرار
    private void updateArtworkDisplay() {
        ivArtwork.setImageResource(images[currentIndex]);
        tvTitle.setText(titles[currentIndex]);
        tvArtistDetails.setText(artists[currentIndex]);
        tvCounter.setText((currentIndex + 1) + "/" + images.length);

        btnPrevious.setEnabled(currentIndex != 0);
        btnNext.setEnabled(currentIndex != images.length - 1);
    }

    // حفظ اللوحة الحالية عند تدوير الشاشة
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);
    }
}