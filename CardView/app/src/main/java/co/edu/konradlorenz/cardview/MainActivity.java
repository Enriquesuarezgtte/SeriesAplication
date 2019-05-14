package co.edu.konradlorenz.cardview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SerieAdapter adapter;
    private List<Serie> serieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        serieList = new ArrayList<>();
        adapter = new SerieAdapter(this, serieList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareSeries();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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

    /**
     * Adding few series for testing
     */
    private void prepareSeries() {
        int[] covers = new int[]{
                R.drawable.serie1,
                R.drawable.serie2,
                R.drawable.serie3,
                R.drawable.serie4,
                R.drawable.serie5,
                R.drawable.serie6,
                R.drawable.serie7,
                R.drawable.serie8,
                R.drawable.serie9};
        HashMap<Integer, List<String>> captsBySeason = new HashMap<>();

        List<String> capts = new ArrayList<>();

        capts.add("LA desaparicio de WIll");
        capts.add("La loca de la calle Maple");
        capts.add("Todo está bien");
        capts.add("EL cuerpo");
        capts.add("La pulga y el acróbata");
        capts.add("EL monstruo");
        capts.add("La bañera");
        capts.add("El otro lado");
        capts.add("MADMAX");
        capts = new ArrayList<>();
        captsBySeason.put(1,capts);
        capts.add("Dulce of truco, bicho raro");
        capts.add("El renacuajo");
        capts.add("Will, el Sabio");
        capts.add("Dig Dug");
        capts.add("El espia");
        capts.add("La hermana perdida");
        capts.add("El azotamentes");
        capts.add("El portal");
        captsBySeason.put(2,capts);
        Serie a = new Serie("Stranger Things",
                2, covers[0], captsBySeason);
        serieList.add(a);

        capts = new ArrayList<>();
        captsBySeason = new HashMap<>();
        capts.add("Days Gone Bye");
        capts.add("Guts");
        capts.add("Tell It to the Frogs");
        capts.add("Vatos");
        capts.add("Wildfire");
        capts.add("TS-19");
        captsBySeason.put(1,capts);
        capts.add("What Lies Ahead");
        capts.add("Bloodletting");
        capts.add("Save the Last One");
        capts.add("Cherokee Rose");
        capts.add("Chupacabra");
        capts.add("Secrets");
        capts.add("Pretty Much Dead Already");
        capts.add("Nebraska");
        capts.add("Triggerfinger");
        capts.add("18 Miles Out");
        capts.add("Judge, Jury, Executioner");
        capts.add("Better Angels");
        capts.add("Beside the Dying Fire");
        captsBySeason.put(2,capts);
        a = new Serie("The Walking Dead", 9, covers[1],captsBySeason);
        serieList.add(a);

        a = new Serie("The Big Bang Theory 5", 12, covers[2],captsBySeason);
        serieList.add(a);

        a = new Serie("Lucifer", 4, covers[3],captsBySeason);
        serieList.add(a);

        a = new Serie("Daredevil", 3, covers[4],captsBySeason);
        serieList.add(a);

        a = new Serie("Game of thrones", 8, covers[5],captsBySeason);
        serieList.add(a);

        a = new Serie("La Casa de las Flores",1, covers[6],captsBySeason);
        serieList.add(a);

        a = new Serie("Orange is the new Black", 6, covers[7],captsBySeason);
        serieList.add(a);

        a = new Serie("House of Cards", 6, covers[8],captsBySeason);
        serieList.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
