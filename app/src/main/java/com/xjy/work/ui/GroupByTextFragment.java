package com.xjy.work.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.xjy.work.GridViewAdapter;
import com.xjy.work.R;
import com.xjy.work.service.ResultService;

public class GroupByTextFragment extends Fragment {

    private ResultService mResultService;
    private GridView mTextGridView;

    public GroupByTextFragment() {
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
        View view = inflater.inflate(R.layout.fragment_group_by_text, container, false);
        mTextGridView = (GridView) view.findViewById(R.id.text_grid);
        mResultService = new ResultService(getActivity());
        mTextGridView.setAdapter(new GridViewAdapter(mResultService.selectGroupBySaveText(), getActivity()));
        return view;
    }


}
