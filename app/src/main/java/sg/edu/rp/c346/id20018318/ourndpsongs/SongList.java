package sg.edu.rp.c346.id20018318.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    Button btnFilter;
    Spinner spinner;
    ListView lv;
    ArrayList<Song> al;
    CustomAdapter aa;
    ArrayList<Year> alYear;
    ArrayAdapter<Year> aaYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        btnFilter = findViewById(R.id.btnFilter);
        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
        aa = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        alYear = new ArrayList<Year>();
        aaYear = new ArrayAdapter<Year>(this, android.R.layout.simple_spinner_dropdown_item, alYear);
        spinner.setAdapter(aaYear);

        DBHelper dbh = new DBHelper(SongList.this);
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        alYear.addAll(dbh.getAllYears());
        aaYear.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent j = new Intent(SongList.this,
                        EditSong.class);

//                int starPos = al.get(position).getStars();
//                j.putExtra("starPos", starPos);

                j.putExtra("data", data);
                startActivity(j);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongList.this);
                al.clear();
                int rating = 5;
                al.addAll(dbh.getAllSongsByStars(rating));
                aa.notifyDataSetChanged();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = spinner.getAdapter().getCount();

                String selectedYear = spinner.getSelectedItem().toString();
                int year = Integer.parseInt(selectedYear);

                DBHelper dbh = new DBHelper(SongList.this);
                int yr = year;

                for (int i = 0; i < count; i++) {
                    if (position == i) {
                        al.clear();
                        al.addAll(dbh.getAllSongsByYear(yr));
                        aa.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(SongList.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        alYear.clear();
        alYear.addAll(dbh.getAllYears());
        aaYear.notifyDataSetChanged();

        dbh.close();
    }
}