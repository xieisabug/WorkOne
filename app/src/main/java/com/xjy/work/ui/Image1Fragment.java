package com.xjy.work.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.xjy.work.R;
import com.xjy.work.utils.ViewHolderUtil;

public class Image1Fragment extends Fragment {

    private OnImage1FragmentInteractionListener mListener;

    public static Integer[] image1s = new Integer[]{R.drawable.circle,
            R.drawable.heart,
            R.drawable.square,
            R.drawable.star,
            R.drawable.triangle};

    public Image1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image1, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.image1_grid);
        Image1Adapter adapter = new Image1Adapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onImage1FragmentInteraction(image1s[position]);
            }
        });
        gridView.setSelector(new ColorDrawable(android.R.color.transparent));
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnImage1FragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnImage1FragmentInteractionListener {
        public void onImage1FragmentInteraction(int resId);
    }

    private class Image1Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return image1s.length;
        }

        @Override
        public Object getItem(int position) {
            return image1s[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(Image1Fragment.this.getActivity(), R.layout.image_grid_item, null);
            }
            ImageView imageView = ViewHolderUtil.get(convertView, R.id.image);
            imageView.setImageResource(image1s[position]);
            return convertView;
        }
    }
}
