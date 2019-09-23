package com.example.android.mybotnav.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.mybotnav.Item.Movie;
import com.example.android.mybotnav.R;
import com.example.android.mybotnav.db.FavoriteHelper;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra";
    public String name, description, date, photo2, genre, tagline, production;
    private ImageView imgPhoto;
    private TextView tvName, tvDescription, tvGenre, tvDate, tvTagline, tvProduction;
    private Movie movie;
    private FavoriteHelper favoriteHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvName = findViewById(R.id.detail_name);
        tvDescription = findViewById(R.id.detail_description);
        tvGenre = findViewById(R.id.detail_genre);
        tvDate = findViewById(R.id.detail_date);
        imgPhoto = findViewById(R.id.detail_photo);
        tvTagline = findViewById(R.id.detail_tagline);
        tvProduction = findViewById(R.id.detail_production_comp);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        name = movie.getName();
        description = movie.getDescription();
        date = movie.getDate();
        photo2 = movie.getPhoto2();
        genre = movie.getAllgenre();
        tagline = movie.getTagline();
        production = movie.getAllproduction();

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + photo2).into(imgPhoto);

        tvName.setText(name);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvProduction.setText(production);
        tvGenre.setText(genre);

        if (!tagline.isEmpty()) {
            tvTagline.setText("\"" + tagline + "\"");
        } else {
            tvTagline.setHeight(0);
        }

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExist(movie)) {
            getMenuInflater().inflate(R.menu.menu_already_favorite, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite) {
            if (!favoriteHelper.isExist(movie)) {
                long result = favoriteHelper.insertFavorite(movie);
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star_clicked);
                    Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.failed__add_favorite), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.favorite_is_exist), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (item.getItemId() == R.id.action_delete_favorite) {
                int result = favoriteHelper.deleteFavorite(movie.getId());
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star);
                    Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.failed__delete_favorite), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
