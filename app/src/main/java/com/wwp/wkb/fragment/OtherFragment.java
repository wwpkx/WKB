package com.wwp.wkb.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wwp.wkb.R;
import com.wwp.wkb.okhttp.adapter.CommonFrameFragmentAdapter;
import com.wwp.wkb.okhttp.base.BaseFragment;
import com.wwp.wkb.other.CalendarActivity;
import com.wwp.wkb.other.DateActivity;
import com.wwp.wkb.other.ImgviewActivity;
import com.wwp.wkb.other.TabMainActivity;
import com.wwp.wkb.other.WebViewActivity;

public class OtherFragment extends BaseFragment {
    private static final String TAG = OtherFragment.class.getSimpleName();

    private ListView mListView;
    private String[] datas;
    private Class<?> [] classs;
    private CommonFrameFragmentAdapter adapter;

    @Override
    protected View initView() {

        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        mListView = (ListView) view.findViewById(R.id.listview);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 点击位置的显示
                String data = datas[position];
                Toast.makeText(mContext, "data==" + data, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, classs[position]);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        //准备数据
        datas = new String[]{"带图标的下tab", "上tab", "Imgview scaleType", "使用网页打开分享activity", "CalendarView", "DatePicker", "WebView"};
        classs = new Class[]{TabMainActivity.class, TabMainActivity.class, ImgviewActivity.class, CalendarActivity.class, CalendarActivity.class, DateActivity.class, WebViewActivity.class};

        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, datas);
        mListView.setAdapter(adapter);
    }
}
