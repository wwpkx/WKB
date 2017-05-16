package com.wwp.wkb.fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wwp.wkb.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoAutoSizeActivity extends Activity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_fresco)
    LinearLayout llFresco;
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_auto_size);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("动态展示图片");

        simpleDraweeView = new SimpleDraweeView(this);
        // 设置宽高比
        simpleDraweeView.setAspectRatio(3.0f);
    }

    @OnClick(R.id.bt_fresco_loadsmall)
    void bt_fresco_loadsmall_click(View view){

        // 图片的地址
        Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");
        // 图片的请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();

        // 加载图片的控制
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();

        // 加载图片
        simpleDraweeView.setController(controller);

        // 添加View到线性布局中
        llFresco.addView(simpleDraweeView);
    }
}
