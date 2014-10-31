package com.xjy.work.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xjy.work.R;
import com.xjy.work.utils.ViewHolderUtil;


public class TextFragment extends Fragment {

    private String[] zodiac = new String[] {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};

    private OnTextFragmentInteractionListener mListener;

    public TextFragment() {
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
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        GridView textGrid = (GridView) view.findViewById(R.id.text_grid);
        textGrid.setAdapter(new TextAdapter());
        textGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onTextFragmentInteraction(zodiac[position]);
            }
        });
        textGrid.setSelector(new ColorDrawable(android.R.color.transparent));
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTextFragmentInteractionListener) activity;
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

    public interface OnTextFragmentInteractionListener {
        public void onTextFragmentInteraction(String text);
    }

    private class TextAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return zodiac.length;
        }

        @Override
        public Object getItem(int position) {
            return zodiac[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(TextFragment.this.getActivity(), R.layout.text_grid_item, null);
            }

            TextView textView = ViewHolderUtil.get(convertView, R.id.text);
            textView.setText(zodiac[position]);
            return convertView;
        }
    }
}
