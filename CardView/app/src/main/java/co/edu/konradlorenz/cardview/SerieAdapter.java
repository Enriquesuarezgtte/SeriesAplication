package co.edu.konradlorenz.cardview;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jiacontrerasp on 02/04/18.
 */
public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.MyViewHolder> {

    private Context mContext;
    private List<Serie> serieList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        private View elementView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            elementView = view;
        }
    }


    public SerieAdapter(Context mContext, List<Serie> serieList) {
        this.mContext = mContext;
        this.serieList = serieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.elementView);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.elementView.clearAnimation();
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Serie serie = serieList.get(position);



        holder.title.setText(serie.getName());
        holder.count.setText(serie.getNumOfSeasons()+""+R.string.series);

        // loading serie cover using Glide library
        Glide.with(mContext).load(serie.getThumbnail()).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        ViewCompat.setTransitionName(holder.thumbnail, serie.getName());
        holder.elementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(view.getContext(), SerieDetailActivity.class);
                intent.putExtra("nombre_Serie", serie.getName());
                intent.putExtra("temporadas_Serie", serie.getNumOfSeasons());
                intent.putExtra("capitulos_Serie", serie.getCaptBySeason());
                intent.putExtra("cover_Serie", serie.getThumbnail());
                intent.putExtra("serie", serie);
                ActivityOptionsCompat optionsCompat =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                (Activity)view.getContext(),
                                new Pair<View, String>(view.findViewById(R.id.thumbnail),mContext.getString(R.string.transition_name_circle)
                                ));
                mContext.startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    /**
     * Mostrar menú emergente al tocar en 3 puntos
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Evento Click listener para el menú emergente
     */
    class MyMenuItemClickListener extends  AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext,R.string.action_add_favourite, Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, R.string.action_play_next, Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return serieList.size();
    }
}
