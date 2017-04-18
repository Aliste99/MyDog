package com.example.thirtyseven.mydog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button beat;
    private Button touch;
    private Button care;
    private TextView textView;
    private ImageView img;
    private ProgressBar progBar;
    Dog dog;
    Bitmap bad;
    Bitmap good;
    Bitmap happy;
    CheckPoint checkPoint;
    FirebaseDatabase database;
    DatabaseReference dogRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        dog = new Dog();
        //dog.setMood(50);
        progBar.setMax(100);
        bad = BitmapFactory.decodeResource(this.getResources(), R.drawable.bitedogee);
        good = BitmapFactory.decodeResource(this.getResources(), R.drawable.normal);
        happy = BitmapFactory.decodeResource(this.getResources(), R.drawable.happy);
        img.setImageBitmap(good);
        database = FirebaseDatabase.getInstance();
        dogRef = database.getReference("dog-status");

    }

    private void init() {
        beat = (Button) findViewById(R.id.beat);
        touch = (Button) findViewById(R.id.touch);
        care = (Button) findViewById(R.id.care);
        textView = (TextView) findViewById(R.id.textView);
        img = (ImageView) findViewById(R.id.imageView);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        beat.setOnClickListener(clickListener);
        touch.setOnClickListener(clickListener);
        care.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.beat:
                    dog.beat();
                    if (dog.willBite() && dog.getMood() < 20) {
                        if (dog.willBite()) {
                            img.setImageBitmap(bad);
                        } else img.setImageBitmap(good);
                        textView.setText("Oops... Your dog bite you");
                    } else {
                        textView.setText("You have beaten your dog :(");
                        if (dog.getMood() > 20) {
                            img.setImageBitmap(good);
                        }
                    }
                    progBar.setProgress(dog.getMood());
                    checkPoint = new CheckPoint(dog.getMood());
                    dogRef.push().setValue(checkPoint);
                    break;
                case R.id.touch:
                    dog.touch();
                    progBar.setProgress(dog.getMood());
                    if (dog.willBite() && dog.getMood() < 20) {
                        if (dog.willBite()) {
                            img.setImageBitmap(bad);
                        } else img.setImageBitmap(good);
                        textView.setText("Oops... Your dog bite you");
                    } else {
                        textView.setText("You have touched your dog. Not so bad");
                        if (dog.getMood() > 20 && dog.getMood() < 80) {
                            img.setImageBitmap(good);
                        } else if (dog.getMood() >= 80) {
                            img.setImageBitmap(happy);
                        }
                    }
                    checkPoint = new CheckPoint(dog.getMood());
                    dogRef.push().setValue(checkPoint);
                    break;
                case R.id.care:
                    dog.care();
                    progBar.setProgress(dog.getMood());
                    if (dog.willBite() && dog.getMood() < 20) {
                        if (dog.willBite()) {
                            img.setImageBitmap(bad);
                        } else img.setImageBitmap(good);
                        textView.setText("Oops... Your dog bite you");
                    } else {
                        textView.setText("Maybe you are not so bad dog owner");
                        if (dog.getMood() > 20 && dog.getMood() < 80) {
                            img.setImageBitmap(good);
                        } else if (dog.getMood() >= 80) {
                            img.setImageBitmap(happy);
                        }
                    }
                    checkPoint = new CheckPoint(dog.getMood());
                    dogRef.push().setValue(checkPoint);
                    break;

            }
        }
    };


}
