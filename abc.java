package com.example.homework6;

//manohar reddy moku
//l20573366



import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class abc extends AppCompatActivity implements View.OnClickListener {
    private ImageView titleImageView;
    private MediaPlayer mediaPlayer;
    private ImageView animalImageView;
    private Button previousButton;
    private Button nextButton;
    private int currentImageIndex = 0;
    private int[] animalImages = {R.drawable.animal1, R.drawable.animal2, R.drawable.animal3,R.drawable.animal4,R.drawable.animal5};
    private SharedPreferences preferences;
    private static final String PREF_CURRENT_IMAGE_INDEX = "current_image_index";
    MediaPlayer mp;
    SoundPool sp;
    int sound_click,sound_growl;
    int num_sounds_loaded;
    boolean sounds_loaded;
    //private ImageView animalImageView;
    private Button infoButton;
    //private Button previousButton;
    //private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getPreferences(MODE_PRIVATE);

        titleImageView = findViewById(R.id.titleImageView);
        animalImageView = findViewById(R.id.animalImageView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        infoButton = findViewById(R.id.infobutton);
        //updateAnimalImage();

        currentImageIndex = preferences.getInt(PREF_CURRENT_IMAGE_INDEX, 0);
        animalImageView.setImageResource(animalImages[currentImageIndex]);
        if (currentImageIndex > 0) {
            titleImageView.setVisibility(View.GONE);
            animalImageView.setVisibility(View.VISIBLE);
        }
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animalInfoUrl = getAnimalInfoUrl(currentImageIndex);
                if (animalInfoUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(animalInfoUrl));
                    startActivity(browserIntent);
                }
            }
        });



//        // Create a MediaPlayer instance
//       // mp = MediaPlayer.create(this, R.raw.jungle);
//
//        // Set the audio file to loop continuously
//        mp.setLooping(true);
//
//        // Start playing the audio file
//        mp.start();

    }

//    private void updateAnimalImage() {
//        animalImageView.setImageResource(animalImages[currentImageIndex]);
//        if (currentImageIndex == 0) {
//            infoButton.setEnabled(false);
//        } else {
//            infoButton.setEnabled(true);
//        }
//    }

    private String getAnimalInfoUrl(int animalIndex) {
        // Replace with actual URLs for each animal
        switch (animalIndex) {
            case 0:
                return "https://www.britannica.com/animal/lion"; // No info available for the first animal
            case 1:
                return "https://www.britannica.com/animal/hippopotamus-mammal-species";
            case 2:
                return "https://www.britannica.com/animal/giraffe";
            case 3:
                return "https://www.britannica.com/animal/zebra";
            case 4:
                return "https://www.britannica.com/animal/monkey";
            default:
                return null;
        }
    }

    @Override
    protected void onPause() {

        if(mp!=null){
            mp.pause();
            mp.release();
            mp=null;
        }
        super.onPause();
        preferences.edit().putInt(PREF_CURRENT_IMAGE_INDEX, currentImageIndex).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentImageIndex = preferences.getInt(PREF_CURRENT_IMAGE_INDEX, 0);
        animalImageView.setImageResource(animalImages[currentImageIndex]);
        if (currentImageIndex > -1) {
            titleImageView.setVisibility(View.GONE);
            animalImageView.setVisibility(View.VISIBLE);
        }
        mp=null;
        mp= MediaPlayer.create(this, R.raw.jungle);
        if(mp!=null){
            mp.setLooping(true);
            mp.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Release the MediaPlayer resources
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentImageIndex", currentImageIndex);

        // Save any necessary data or state to the outState Bundle
    }




    public void showPreviousImage(View view) {
        if (currentImageIndex > 0) {
            currentImageIndex--;
            animalImageView.setImageResource(animalImages[currentImageIndex]);
            titleImageView.setVisibility(View.GONE);
            animalImageView.setVisibility(View.VISIBLE);
        }
    }

    public void showNextImage(View view) {
        if (currentImageIndex < animalImages.length - 1) {
            currentImageIndex++;
            animalImageView.setImageResource(animalImages[currentImageIndex]);
            titleImageView.setVisibility(View.GONE);
            animalImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
