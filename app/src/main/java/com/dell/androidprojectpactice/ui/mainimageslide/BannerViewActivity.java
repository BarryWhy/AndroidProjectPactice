package com.dell.androidprojectpactice.ui.mainimageslide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.wangyuwei.banner.BannerEntity;
import me.wangyuwei.banner.BannerView;

/**
 * Created by dell on 2016/7/20.
 */
@LayoutBinder(R.layout.ac_main_image_slide)
public class BannerViewActivity extends AppCompatActivity {
    @Bind(R.id.iv_banner)
    BannerView ivBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
        initBannerEntity();
        initBanner();
    }

    private void initBanner() {
        List<BannerEntity> list = initBannerEntity();
        ivBanner.setEntities(list);
        ivBanner.setOnBannerClickListener(position -> Toast.makeText(BannerViewActivity.this, "This is the" + position + " image " + list.get(position).title, Toast.LENGTH_LONG).show());
    }

    private List<BannerEntity> initBannerEntity() {
        List<BannerEntity> bannerImages = new ArrayList<>();
        BannerEntity bannerImage0 = new BannerEntity();
        bannerImage0.imageUrl = "http://222.168.20.34:11023/ftp/mole/81ae700a-b637-4a27-8e98-666d95025f1c.jpg";
        bannerImage0.title = "image0";
        bannerImages.add(bannerImage0);

        BannerEntity bannerImage1 = new BannerEntity();
        bannerImage1.imageUrl = "http://222.168.20.34:11023/ftp/mole/81ae700a-b637-4a27-8e98-666d95025f1c.jpg";
        bannerImage1.title = "image1";
        bannerImages.add(bannerImage1);

        BannerEntity bannerImage2 = new BannerEntity();
        bannerImage2.imageUrl = "http://222.168.20.34:11023/ftp/mole/81ae700a-b637-4a27-8e98-666d95025f1c.jpg";
        bannerImage2.title = "image2";
        bannerImages.add(bannerImage2);

        return bannerImages;
    }
}
