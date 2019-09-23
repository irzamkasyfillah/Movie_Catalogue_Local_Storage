package com.example.android.mybotnav.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.mybotnav.Item.TVShow;
import com.example.android.mybotnav.R;
import com.example.android.mybotnav.db.FavoriteHelper;

public class DetailTVShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "string_extra";
    public String name, description, date, photo2, genre, status, production;
    private ImageView imgPhoto;
    private TextView tvName, tvDescription, tvGenre, tvDate, tvStatus, tvProduction;
    private TVShow tvShow;
    private FavoriteHelper favoriteHelper;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite) {
            if (!favoriteHelper.isExist2(tvShow)) {
                long result = favoriteHelper.insertFavorite2(tvShow);
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star_clicked);
                    Toast.makeText(DetailTVShowActivity.this, getResources().getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailTVShowActivity.this, getResources().getString(R.string.failed__add_favorite), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DetailTVShowActivity.this, getResources().getString(R.string.favorite_is_exist), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (item.getItemId() == R.id.action_delete_favorite) {
                int result = favoriteHelper.deleteFavorite2(tvShow.getId());
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star);
                    Toast.makeText(DetailTVShowActivity.this, getResources().getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailTVShowActivity.this, getResources().getString(R.string.failed__delete_favorite), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExist2(tvShow)) {
            getMenuInflater().inflate(R.menu.menu_already_favorite, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);

        tvName = findViewById(R.id.detail_name);
        tvDescription = findViewById(R.id.detail_description);
        tvGenre = findViewById(R.id.detail_genre);
        tvDate = findViewById(R.id.detail_date);
        tvStatus = findViewById(R.id.detail_status);
        imgPhoto = findViewById(R.id.detail_photo);
        tvProduction = findViewById(R.id.detail_production_comp);

        tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        name = tvShow.getName();
        description = tvShow.getDescription();
        date = tvShow.getDate();
        photo2 = tvShow.getPhoto2();
        genre = tvShow.getAllgenre();
        status = tvShow.getStatus();
        production = tvShow.getAllproduction();

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + photo2).into(imgPhoto);
        tvName.setText(name);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvProduction.setText(production);
        tvGenre.setText(genre);
        tvStatus.setText(status);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
    }
}
