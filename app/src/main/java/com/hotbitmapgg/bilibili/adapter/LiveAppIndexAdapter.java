package com.hotbitmapgg.bilibili.adapter;

import android.content.Context;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.hotbitmapgg.bilibili.entity.live.LiveAppIndexInfo;
import com.hotbitmapgg.bilibili.module.home.live.model.BannerModel;
import com.hotbitmapgg.bilibili.module.home.live.model.LiveEntranceModel;
import com.hotbitmapgg.bilibili.module.home.live.model.LiveItemModel;
import com.hotbitmapgg.bilibili.module.home.live.model.PartitionModel;
import com.hotbitmapgg.bilibili.widget.banner.BannerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by hcc on 16/8/4 14:12
 * 100332338@qq.com
 * <p/>
 * 首页直播adapter
 */
public class LiveAppIndexAdapter extends EpoxyAdapter {

    private final Context context;

    final List<EpoxyModel> epoxyModels = new ArrayList<>();


    public LiveAppIndexAdapter(Context context) {
        enableDiffing();
        this.context = context;
    }


    public void setLiveInfo(LiveAppIndexInfo liveAppIndexInfo) {

        epoxyModels.clear();

        List<LiveAppIndexInfo.DataBean.BannerBean> banner = liveAppIndexInfo.getData().getBanner();

        List<BannerEntity> banners = new ArrayList<>();

        Observable.from(banner)
                .forEach(bannerBean -> {
                    BannerEntity bannerEntity = new BannerEntity(bannerBean.getLink(), bannerBean.getTitle(), bannerBean.getImg());
                    banners.add(bannerEntity);
                });

        epoxyModels.add(new BannerModel(banners));


        for (int i = 0; i < 4; i++) {
            epoxyModels.add(new LiveEntranceModel(i));
        }


        Observable.from(liveAppIndexInfo.getData().getPartitions())
                .forEach(new Action1<LiveAppIndexInfo.DataBean.PartitionsBean>() {
                    @Override
                    public void call(LiveAppIndexInfo.DataBean.PartitionsBean partitionsBean) {
                        epoxyModels.add(new PartitionModel(partitionsBean.getPartition()));
                        for (int i = 0; i < partitionsBean.getLives().size(); i++) {
                            epoxyModels.add(new LiveItemModel(partitionsBean.getLives().get(i)));
                        }
                    }
                });

        if (isEmpty()) {
            addModels((Collection) epoxyModels);
        } else {
            notifyModelsChanged();
        }
    }


}
