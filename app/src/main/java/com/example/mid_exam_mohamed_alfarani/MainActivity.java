
package com.example.mid_exam_mohamed_alfarani;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView artworkCounter;
    private TextView artworkTitle;
    private TextView artworkArtistYear;

    private ImageView artworkImage;

    private Button previousButton;
    private Button nextButton;

    private int currentArtworkIndex = 0;

    // Artwork images
    private final int[] artworkImages = {
            R.drawable.artwork_1,
            R.drawable.artwork_2,
            R.drawable.artwork_3,
            R.drawable.artwork_4,
            R.drawable.artwork_5,
            R.drawable.artwork_6
    };

    // Artwork titles
    private final String[] artworkTitles = {
            "Deep Field Stellar Deaths",
            "Cosmic Cliffs",
            "Pillars of Creation",
            "Southern Ring Nebula",
            "Cartwheel Galaxy",
            "Hubble NGC 346"
    };

    // Artwork artists
    private final String[] artworkArtists = {
            "NASA / ESA / CSA",
            "NASA / ESA / CSA",
            "NASA / ESA / CSA",
            "NASA / ESA / CSA",
            "NASA / ESA / CSA",
            "ESA / Hubble / NASA"
    };

    // Artwork years
    private final String[] artworkYears = {
            "2022",
            "2022",
            "2022",
            "2022",
            "2022",
            "2025"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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
        artworkCounter = findViewById(
                R.id.artworkCounter
        );

        artworkTitle = findViewById(
                R.id.artworkTitle
        );

        artworkArtistYear = findViewById(
                R.id.artworkArtistYear
        );

        artworkImage = findViewById(
                R.id.artworkImage
        );

        previousButton = findViewById(
                R.id.previousButton
        );

        nextButton = findViewById(
                R.id.nextButton
        );

        // Restore saved artwork position
        if (savedInstanceState != null) {
            currentArtworkIndex = savedInstanceState.getInt(
                    "artwork_index",
                    0
            );
        }

        // Display current artwork
        updateArtwork();

        // Previous button
        previousButton.setOnClickListener(v -> {

            if (currentArtworkIndex > 0) {
                currentArtworkIndex--;
                updateArtwork();
            }

        });

        // Next button
        nextButton.setOnClickListener(v -> {

            if (currentArtworkIndex < artworkImages.length - 1) {
                currentArtworkIndex++;
                updateArtwork();
            }

        });

        // Open ArtworkDetailsActivity
        artworkImage.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            // Send artwork information
            intent.putExtra(
                    "artwork_title",
                    artworkTitles[currentArtworkIndex]
            );

            intent.putExtra(
                    "artist_name",
                    artworkArtists[currentArtworkIndex]
            );

            intent.putExtra(
                    "creation_year",
                    artworkYears[currentArtworkIndex]
            );

            startActivity(intent);

        });
    }

    // Update artwork information
    private void updateArtwork() {

        artworkImage.setImageResource(
                artworkImages[currentArtworkIndex]
        );

        artworkImage.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        artworkImage.setBackgroundColor(
                Color.TRANSPARENT
        );

        artworkTitle.setText(
                artworkTitles[currentArtworkIndex]
        );

        artworkArtistYear.setText(
                artworkArtists[currentArtworkIndex]
                        + " ("
                        + artworkYears[currentArtworkIndex]
                        + ")"
        );

        artworkCounter.setText(
                (currentArtworkIndex + 1)
                        + " / "
                        + artworkImages.length
        );

        // Disable Previous at first artwork
        previousButton.setEnabled(
                currentArtworkIndex > 0
        );

        // Disable Next at last artwork
        nextButton.setEnabled(
                currentArtworkIndex < artworkImages.length - 1
        );
    }

    // Save current artwork position
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(
                "artwork_index",
                currentArtworkIndex
        );

        super.onSaveInstanceState(outState);
    }
}
