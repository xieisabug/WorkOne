package com.xjy.work.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.xjy.work.R;
import com.xjy.work.utils.ViewHolderUtil;

public class Image2Fragment extends Fragment {

    private OnImage2FragmentInteractionListener mListener;

    public static Integer[] image2s = new Integer[]{R.drawable.red_heart,
            R.drawable.red_plus,
            R.drawable.red_star};

    public Image2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image2, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.image2_grid);
        gridView.setAdapter(new Image2Adapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onImage2FragmentInteraction(image2s[position]);
            }
        });
        gridView.setSelector(new ColorDrawable(android.R.color.transparent));

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnImage2FragmentInteractionListener) activity;
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

    public interface OnImage2FragmentInteractionListener {
        public void onImage2FragmentInteraction(int resId);
    }

    private class Image2Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return image2s.length;
        }

        @Override
        public Object getItem(int position) {
            return image2s[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(Image2Fragment.this.getActivity(), R.layout.image_grid_item, null);
            }
            ImageView imageView = ViewHolderUtil.get(convertView, R.id.image);
            imageView.setImageResource(image2s[position]);
            return convertView;
        }
    }
}
