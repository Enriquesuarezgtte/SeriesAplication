package co.edu.konradlorenz.cardview;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alespero.expandablecardview.ExpandableCardView;

import java.util.ArrayList;
import java.util.List;


public class DynamicFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private CapitulosAdapter capitulosAdapter;
    private ExpandableCardView expandableCardView;
    public static DynamicFragment newInstance(int val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        fragment.setArguments(args);
        return fragment;
    }

    int val;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        val = getArguments().getInt("someInt", 0);

        final FragmentActivity c = getActivity();
        expandableCardView = view.findViewById(R.id.expands_capts);
        recyclerView = view.findViewById(R.id.recycler_capts);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
       Serie cap= (Serie) getArguments().getSerializable("capitulos");
       capitulosAdapter = new CapitulosAdapter(c,cap.getCaptBySeason().get(val+1));
        recyclerView.setAdapter(capitulosAdapter);
        capitulosAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        animateCircularReveal(view);
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