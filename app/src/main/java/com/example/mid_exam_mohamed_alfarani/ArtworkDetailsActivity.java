
package com.example.mid_exam_mohamed_alfarani;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private TextView artworkDetailsText;
    private Button backButton;
    private Button visitWebsiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Connect Java with XML
        artworkDetailsText = findViewById(
                R.id.artworkDetailsText
        );

        backButton = findViewById(
                R.id.backButton
        );

        visitWebsiteButton = findViewById(
                R.id.visitWebsiteButton
        );

        // Receive information from MainActivity
        Intent intent = getIntent();

        String title = intent.getStringExtra(
                "artwork_title"
        );

        String artist = intent.getStringExtra(
                "artist_name"
        );

        String year = intent.getStringExtra(
                "creation_year"
        );

        // Display received information
        String details =
                "Title: " + title
                        + "\n\nArtist: " + artist
                        + "\n\nYear: " + year;

        artworkDetailsText.setText(details);

        // Return to MainActivity
        backButton.setOnClickListener(v -> {
            finish();
        });

        // Open an external website
        visitWebsiteButton.setOnClickListener(v -> {

            Intent websiteIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://science.nasa.gov/")
            );

            try {
                startActivity(websiteIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        this,
                        "No browser available",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
    }
}
