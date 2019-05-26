package co.edu.konradlorenz.cardview;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.alespero.expandablecardview.ExpandableCardView;

import java.util.List;

public class CapitulosAdapter extends RecyclerView.Adapter<CapitulosAdapter.MyViewHolder>{
    private Context mContext;
    List<String> capitulos;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView capts;
        public ExpandableCardView cardView;
        private View elementView;

        public MyViewHolder(View view) {
            super(view);
            capts = view.findViewById(R.id.capts);
            cardView =view.findViewById(R.id.expands_capts);
            elementView = view;
        }
    }
    @Override
    public CapitulosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.capitulos, parent, false);

        return new CapitulosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

    holder.cardView.setTitle(capitulos.get(position));
        holder.capts.setText("Érase una vez... una rompedora de cadenas que se volvió loca");
    }

    @Override
    public int getItemCount() {
        return capitulos.size();
    }

    public CapitulosAdapter(Context context,List<String> capitulos) {
        this.capitulos = capitulos;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull CapitulosAdapter.MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CapitulosAdapter.MyViewHolder holder) {
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
}
