package com.wwp.wkb.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.wwp.wkb.R;
import com.wwp.wkb.fragment.CommonFrameFragment;
import com.wwp.wkb.fragment.CustomFragment;
import com.wwp.wkb.fragment.OtherFragment;
import com.wwp.wkb.fragment.ThirdPartyFragment;
import com.wwp.wkb.okhttp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.wwp.wkb.R.id.rb_common_frame;

public class TabTopActivity extends FragmentActivity {

    @Bind(R.id.tab_top)
    android.support.design.widget.TabLayout tabTop;
    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                //根据位置得到对应的Fragment
                BaseFragment to = getFragment();
                //替换
                switchFrament(curFragment, to);
            }
        };
        findViewById(R.id.rb_common_frame).setOnClickListener(clickListener);
        findViewById(R.id.rb_thirdparty).setOnClickListener(clickListener);
        findViewById(R.id.rb_custom).setOnClickListener(clickListener);
        findViewById(R.id.rb_other).setOnClickListener(clickListener);
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            curFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

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

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new CommonFrameFragment());//常用框架Fragment
        mBaseFragment.add(new ThirdPartyFragment());//第三方Fragment
        mBaseFragment.add(new CustomFragment());//自定义控件Fragment
        mBaseFragment.add(new OtherFragment());//其他Fragment
    }

    private void initView() {
        setContentView(R.layout.activity_tab_top);
    }
}
