package com.chen.viewpage2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.to.aboomy.pager2banner.Banner;
import com.to.aboomy.pager2banner.IndicatorView;
import com.to.aboomy.pager2banner.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> images = new ArrayList<>();
        images.add("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1035415831,1465727770&fm=26&gp=0.jpg");
        images.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1694681277,1453280371&fm=26&gp=0.jpg");
        images.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2104801820,2623271160&fm=111&gp=0.jpg");

        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        ImageAdapter pager2Adapter = new ImageAdapter(this);
        pager2Adapter.addData(images);
        viewPager2.setAdapter(pager2Adapter);

        Banner banner = findViewById(R.id.banner);

        //使用内置Indicator
        IndicatorView indicator = new IndicatorView(this)
                .setIndicatorColor(Color.DKGRAY)
                .setIndicatorSelectorColor(Color.WHITE);

        //传入RecyclerView.Adapter 即可实现无限轮播
        banner.setIndicator(indicator)
                .setAdapter(pager2Adapter);

        //设置左右页面露出来的宽度及item与item之间的宽度
        banner.setPageMargin(ConvertUtils.dp2px(20), ConvertUtils.dp2px(10))
                //内置ScaleInTransformer，设置切换缩放动画
                .setPageTransformer(new ScaleInTransformer());

    }
}
