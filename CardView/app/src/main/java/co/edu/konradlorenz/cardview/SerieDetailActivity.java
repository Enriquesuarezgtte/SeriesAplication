package co.edu.konradlorenz.cardview;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SerieDetailActivity extends AppCompatActivity {
    private LinearLayout linearLayout;

    private List<Serie> serieList;
    private ImageView imagen_album;
    private TextView nombre;
    private TextView num_temporadas;
    private View view;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        initCollapsingToolbar();

        linearLayout=findViewById(R.id.detail_lin);
        imagen_album=findViewById(R.id.backdrop_detail);
        nombre=findViewById(R.id.nombre_detail);
        num_temporadas=findViewById(R.id.love_serie);
        viewPager=findViewById(R.id.frameLayout);

//
        tabs = findViewById(R.id.tabsseasons);
        serieList = new ArrayList<>();

        setUpData();

    }

    private void setUpData() {
        String nombr = getIntent().getStringExtra("nombre_serie");
        int numero_t = getIntent().getIntExtra("temporadas_Serie",1);
        int capitulos_serie = getIntent().getIntExtra("temporadas_Serie",1);
        int imagen = getIntent().getIntExtra("cover_Serie", 1);
        Serie serie = (Serie) getIntent().getSerializableExtra("serie");
        Glide.with(this).load(imagen).into(imagen_album);


        nombre.setText(nombr);
        for (int i=1; i<=serie.getNumOfSeasons(); i++){
            tabs.addTab(tabs.newTab().setText(i+""));
        }

        SeasonAdapter adapter = new SeasonAdapter(getSupportFragmentManager(), tabs.getTabCount(),serie);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        if (tabs.getTabCount() == 4) {
            tabs.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_detail);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbardetail);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
