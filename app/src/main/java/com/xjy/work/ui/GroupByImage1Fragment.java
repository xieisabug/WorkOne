package com.xjy.work.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xjy.work.GridViewAdapter;
import com.xjy.work.R;
import com.xjy.work.service.ResultService;

public class GroupByImage1Fragment extends Fragment {

    public GroupByImage1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_by_image, container, false);
        GridView textGridView = (GridView) view.findViewById(R.id.text_grid);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.example_image);
        for (int i = 0; i<Image1Fragment.image1s.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(20,20);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(Image1Fragment.image1s[i]);
            linearLayout.addView(imageView);
        }

        ResultService resultService = new ResultService(getActivity());
        textGridView.setAdapter(new GridViewAdapter(resultService.selectGroupBySaveImage1(), getActivity()));
        return view;
    }


}
