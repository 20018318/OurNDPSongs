package sg.edu.rp.c346.id20018318.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class EditSong extends AppCompatActivity {

    EditText etId, etTitle, etSingers, etYear;
    RatingBar rbStars;
//    RadioGroup rgStars;
//    RadioButton star1, star2, star3, star4 ,star5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        etId = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rbStars = findViewById(R.id.ratingBar3);
//        rgStars = findViewById(R.id.rgStars);
//        star1 = findViewById(R.id.stars1);
//        star2 = findViewById(R.id.stars2);
//        star3 = findViewById(R.id.stars3);
//        star4 = findViewById(R.id.stars4);
//        star5 = findViewById(R.id.stars5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent j = getIntent();
        data = (Song) j.getSerializableExtra("data");

        etId.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));
        rbStars.setRating(data.getStars());
//        int starPos = j.getIntExtra("starPos", 0);
//        if (starPos == 1) {
//            star1.setChecked(true);
//        } else if (starPos == 2) {
//            star2.setChecked(true);
//        } else if (starPos == 3) {
//            star3.setChecked(true);
//        } else if (starPos == 4) {
//            star4.setChecked(true);
//        } else if (starPos == 5) {
//            star5.setChecked(true);
//        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
//                int selectedId = rgStars.getCheckedRadioButtonId();
//                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
//                data.setStars(Integer.parseInt(selectedButton.getText().toString()));
                data.setStars((int) rbStars.getRating());

                dbh.updateSong(data);

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.getId());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}