package sg.edu.rp.c346.id20018318.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RatingBar rbStars;
//    RadioGroup rgStars;
//    RadioButton star1, star2, star3, star4 ,star5;
    Button btnInsert, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rbStars = findViewById(R.id.ratingBar);
//        rgStars = findViewById(R.id.rgStars);
//        star1 = findViewById(R.id.stars1);
//        star2 = findViewById(R.id.stars2);
//        star3 = findViewById(R.id.stars3);
//        star4 = findViewById(R.id.stars4);
//        star5 = findViewById(R.id.stars5);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitle.getText().toString().trim().isEmpty() || etSingers.getText().toString().trim().isEmpty() || etYear.getText().toString().trim().isEmpty() || rbStars.getRating() == 0.0) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String title = etTitle.getText().toString();
                    String singers = etSingers.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
//                  int selectedId = rgStars.getCheckedRadioButtonId();
//                  RadioButton selectedButton = (RadioButton) findViewById(selectedId);
//                  int stars = Integer.parseInt(selectedButton.getText().toString());
                    int stars = (int) rbStars.getRating();
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertSong(title, singers, year, stars);
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();

                    etTitle.setText("");
                    etSingers.setText("");
                    etYear.setText("");
//                  rgStars.clearCheck();
                    rbStars.setRating(0);
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SongList.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String title = etTitle.getText().toString();
        String singers = etSingers.getText().toString();
        String year = etYear.getText().toString();
//        int selectedId = rgStars.getCheckedRadioButtonId();
//        RadioButton selectedButton = (RadioButton) findViewById(selectedId);
//        int stars = Integer.parseInt(selectedButton.getText().toString());
        int stars = (int) rbStars.getRating();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("title", title);
        prefEdit.putString("singers", singers);
        prefEdit.putString("year", year);
        prefEdit.putInt("stars", stars);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String title = prefs.getString("title", "");
        String singers = prefs.getString("singers", "");
        String year = prefs.getString("year", "");
        int stars = prefs.getInt("stars", 0);

        etTitle.setText(title);
        etSingers.setText(singers);
        etYear.setText(year);
//        if (stars == 1) {
//            star1.setChecked(true);
//        } else if (stars == 2) {
//            star2.setChecked(true);
//        } else if (stars == 3) {
//            star3.setChecked(true);
//        } else if (stars == 4) {
//            star4.setChecked(true);
//        } else if (stars == 5) {
//            star5.setChecked(true);
//        }
        rbStars.setRating(stars);
    }
}