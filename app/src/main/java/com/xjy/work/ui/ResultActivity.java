package com.xjy.work.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xjy.work.R;
import com.xjy.work.service.ResultService;
import com.xjy.work.utils.ViewHolderUtil;

import java.util.List;
import java.util.Map;

public class ResultActivity extends ActionBarActivity implements View.OnClickListener{

    private GroupByTextFragment mGroupByTextFragment;
    private GroupByImage1Fragment mGroupByImage1Fragment;
    private GroupByImage2Fragment mGroupByImage2Fragment;

    //当前Fragment
    private Fragment from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        PlaceholderFragment placeholderFragment = new PlaceholderFragment();
        from = placeholderFragment;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,placeholderFragment)
                    .commit();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_result);
        actionBar.getCustomView().findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });

        initView();
    }

    private void initView() {
        RadioButton textRadioButton = (RadioButton) findViewById(R.id.button_text);
        RadioButton image1RadioButton = (RadioButton) findViewById(R.id.button_image1);
        RadioButton image2RadioButton = (RadioButton) findViewById(R.id.button_image2);

        textRadioButton.setOnClickListener(this);
        image1RadioButton.setOnClickListener(this);
        image2RadioButton.setOnClickListener(this);

    }

    /**
     * 切换Fragment
     *
     * @param from 从哪个fragment切换
     * @param to   切换到的fragment
     */
    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        if (!to.isAdded()) {
            fragmentTransaction.hide(from).add(R.id.container, to).commit();
        } else {
            fragmentTransaction.hide(from).show(to).commit();
        }
        this.from = to;
    }


    @Override
    public void onClick(View v) {
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.title)).setText("分类结果");
        switch (v.getId()) {
            case R.id.button_text:
                if (from != mGroupByTextFragment) {
                    if (mGroupByTextFragment == null) {
                        mGroupByTextFragment = new GroupByTextFragment();
                    }
                    switchFragment(from, mGroupByTextFragment);
                }
                break;
            case R.id.button_image1:
                if (from != mGroupByImage1Fragment) {
                    if (mGroupByImage1Fragment == null) {
                        mGroupByImage1Fragment = new GroupByImage1Fragment();
                    }
                    switchFragment(from, mGroupByImage1Fragment);
                }
                break;
            case R.id.button_image2:
                if (from != mGroupByImage2Fragment) {
                    if (mGroupByImage2Fragment == null) {
                        mGroupByImage2Fragment = new GroupByImage2Fragment();
                    }
                    switchFragment(from, mGroupByImage2Fragment);
                }
                break;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ListView mListView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_result, container, false);
            mListView = (ListView) rootView.findViewById(R.id.result_list);
            ResultService mResultService = new ResultService(PlaceholderFragment.this.getActivity());
            mListView.setAdapter(new ResultAdapter(mResultService.selectAll()));
            return rootView;
        }

        private class ResultAdapter extends BaseAdapter {

            private List<Map<String, Object>> mMapList;

            private ResultAdapter(List<Map<String, Object>> list) {
                mMapList = list;
            }

            @Override
            public int getCount() {
                return mMapList.size();
            }

            @Override
            public Object getItem(int position) {
                return mMapList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(PlaceholderFragment.this.getActivity(), R.layout.result_list_item, null);
                }

                ImageView image1 = ViewHolderUtil.get(convertView, R.id.image1);
                ImageView image2 = ViewHolderUtil.get(convertView, R.id.image2);
                TextView textView = ViewHolderUtil.get(convertView, R.id.text);
                TextView idTextView = ViewHolderUtil.get(convertView, R.id.id);

                idTextView.setText(mMapList.get(position).get("_id").toString());
                textView.setText(mMapList.get(position).get("save_text").toString());
                image1.setImageResource(((Integer) mMapList.get(position).get("save_image1")));
                image2.setImageResource(((Integer) mMapList.get(position).get("save_image2")));
                return convertView;
            }
        }
    }
}
