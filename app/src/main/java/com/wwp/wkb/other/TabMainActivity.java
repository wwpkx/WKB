package com.wwp.wkb.other;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wwp.wkb.R;
import com.wwp.wkb.fragment.CommonFrameFragment;
import com.wwp.wkb.fragment.CustomFragment;
import com.wwp.wkb.fragment.OtherFragment;
import com.wwp.wkb.fragment.ThirdPartyFragment;
import com.wwp.wkb.okhttp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.wwp.wkb.R.id.rb_common_frame;

public class TabMainActivity extends FragmentActivity {
    private List<BaseFragment> mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();

        switchFrament(0);
        checkBtn(0);
    }

    private void setListener() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = 0;
                switch (view.getId()) {
                    case rb_common_frame://常用框架
                        position = 0;
                        break;
                    case R.id.rb_thirdparty://第三方
                        position = 1;
                        break;
                    case R.id.rb_custom://自定义
                        position = 2;
                        break;
                    case R.id.rb_other://其他
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                switchFrament(position);
                checkBtn(position);
            }
        };

        findViewById(R.id.rb_common_frame).setOnClickListener(clickListener);
        findViewById(R.id.rb_thirdparty).setOnClickListener(clickListener);
        findViewById(R.id.rb_custom).setOnClickListener(clickListener);
        findViewById(R.id.rb_other).setOnClickListener(clickListener);
    }

    private void checkBtn(int position) {
        LinearLayout viewMainBtn = (LinearLayout) findViewById(R.id.ll_main_btn);
        for (int i = 0; i < viewMainBtn.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) viewMainBtn.getChildAt(i);
            for (int j = 0; j < viewGroup.getChildCount(); j++) {
                viewGroup.getChildAt(j).setSelected(position == i);
            }
        }
    }

    private void switchFrament(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mBaseFragment.size(); i++) {
            if (position == i) {
                fragmentTransaction.show(mBaseFragment.get(i));
            } else {
                fragmentTransaction.hide(mBaseFragment.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * 只显示一个fragment
     *
     * @param fragment
     */
    private void switchFrament(BaseFragment fragment) {
        //1.得到FragmentManger
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        //3.替换
        transaction.replace(R.id.fl_content, fragment);
        //4.提交事务
        transaction.commit();
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new CommonFrameFragment());//常用框架Fragment
        mBaseFragment.add(new ThirdPartyFragment());//第三方Fragment
        mBaseFragment.add(new CustomFragment());//自定义控件Fragment
        mBaseFragment.add(new OtherFragment());//其他Fragment

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mBaseFragment.size(); i++) {
            fragmentTransaction.add(R.id.fl_content, mBaseFragment.get(i));
        }
        fragmentTransaction.commit();
    }

    private void initView() {
        setContentView(R.layout.activity_tab_main);
    }
}
