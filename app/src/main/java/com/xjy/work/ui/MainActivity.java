package com.xjy.work.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjy.work.R;
import com.xjy.work.service.ResultService;
import com.xjy.work.utils.ViewHolderUtil;

import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,
        TextFragment.OnTextFragmentInteractionListener, Image1Fragment.OnImage1FragmentInteractionListener,
        Image2Fragment.OnImage2FragmentInteractionListener {

    //TextFragment
    private TextFragment mTextFragment;
    //Image1Fragment
    private Image1Fragment mImage1Fragment;
    //Image2Fragment
    private Image2Fragment mImage2Fragment;
    //当前Fragment
    private Fragment from;

    private ListView mHistoryListView;

    private TextView mTextView;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private int mImage1Res;

    private int mImage2Res;

    private ResultService mResultService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultService = new ResultService(this);
        mTextFragment = new TextFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.view_container, mTextFragment)
                    .commit();
            from = mTextFragment;
        }
        initActionBar();
        initView();
    }

    /**
     * 初始化view，注册事件
     */
    private void initView() {
        RadioButton textRadioButton = (RadioButton) findViewById(R.id.button_text);
        RadioButton image1RadioButton = (RadioButton) findViewById(R.id.button_image1);
        RadioButton image2RadioButton = (RadioButton) findViewById(R.id.button_image2);

        textRadioButton.setOnClickListener(this);
        image1RadioButton.setOnClickListener(this);
        image2RadioButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text);
        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);
        Button sureButton = (Button) findViewById(R.id.sure);
        Button resultButton = (Button) findViewById(R.id.result);

        sureButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);

        RelativeLayout slideUp = (RelativeLayout) findViewById(R.id.slide_up);
        RelativeLayout slideDown = (RelativeLayout) findViewById(R.id.slide_down);

        slideUp.setOnClickListener(this);
        slideDown.setOnClickListener(this);
        mHistoryListView = (ListView) findViewById(R.id.history);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("作业");
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
            fragmentTransaction.hide(from).add(R.id.view_container, to).commit();
        } else {
            fragmentTransaction.hide(from).show(to).commit();
        }
        this.from = to;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //文字选择切换
            case R.id.button_text:
                if (from != mTextFragment) {
                    switchFragment(from, mTextFragment);
                }
                break;
            //图片1选择切换
            case R.id.button_image1:
                if (from != mImage1Fragment) {
                    if (mImage1Fragment == null) {
                        mImage1Fragment = new Image1Fragment();
                    }
                    switchFragment(from, mImage1Fragment);
                }
                break;
            //图片2选择切换
            case R.id.button_image2:
                if (from != mImage2Fragment) {
                    if (mImage2Fragment == null) {
                        mImage2Fragment = new Image2Fragment();
                    }
                    switchFragment(from, mImage2Fragment);
                }
                break;
            case R.id.sure:
                if (TextUtils.isEmpty(mTextView.getText()) || mImage1Res == 0 || mImage2Res == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("错误");
                    builder.setMessage("还没选择完全，请确认是否选择了文字、图片1、图片2.");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    mResultService.insertResult(mTextView.getText().toString(), mImage1Res, mImage2Res);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("成功");
                    builder.setMessage("保存成功！");
                    builder.setNegativeButton("确定并返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mTextView.setText("");
                            mImageView1.setImageResource(0);
                            mImageView2.setImageResource(0);
                            mImage1Res = 0;
                            mImage2Res = 0;
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
                break;
            //结果按钮
            case R.id.result:
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
            //上拉按钮
            case R.id.slide_up:
                findViewById(R.id.history_container).setVisibility(View.GONE);
                break;
            //下拉按钮
            case R.id.slide_down:
                findViewById(R.id.history_container).setVisibility(View.VISIBLE);
                mHistoryListView.setAdapter(new HistoryListViewAdapter(mResultService.selectAllByLast()));
                break;
        }
    }

    @Override
    public void onTextFragmentInteraction(String text) {
        mTextView.setText(text);
        if (mImage1Fragment == null) {
            mImage1Fragment = new Image1Fragment();
        }
        switchFragment(from, mImage1Fragment);
    }

    @Override
    public void onImage1FragmentInteraction(int resId) {
        mImageView1.setImageResource(resId);
        mImage1Res = resId;
        if (mImage2Fragment == null) {
            mImage2Fragment = new Image2Fragment();
        }
        switchFragment(from, mImage2Fragment);
    }

    @Override
    public void onImage2FragmentInteraction(int resId) {
        mImageView2.setImageResource(resId);
        mImage2Res = resId;
    }

    private class HistoryListViewAdapter extends BaseAdapter {

        List<Map<String, Object>> mMapList;

        private HistoryListViewAdapter(List<Map<String, Object>> list) {
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
                convertView = View.inflate(MainActivity.this, R.layout.history_list_view_item, null);
            }
            TextView textView = ViewHolderUtil.get(convertView, R.id.text);
            ImageView imageView1 = ViewHolderUtil.get(convertView, R.id.image1);
            ImageView imageView2 = ViewHolderUtil.get(convertView, R.id.image2);

            textView.setText(mMapList.get(position).get("save_text").toString());
            imageView1.setImageResource(((Integer) mMapList.get(position).get("save_image1")));
            imageView2.setImageResource(((Integer) mMapList.get(position).get("save_image2")));

            return convertView;
        }
    }
}
