package com.hotbitmapgg.bilibili.module.home.live.model;

import android.view.View;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.hotbitmapgg.bilibili.widget.banner.BannerEntity;
import com.hotbitmapgg.bilibili.widget.banner.BannerView;
import com.hotbitmapgg.ohmybilibili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sword on 2017/4/6.
 */

public class BannerModel extends EpoxyModelWithHolder<BannerModel.ViewHodler> {

    List<BannerEntity> list;

    public BannerModel(List<BannerEntity> list) {
        this.list = list;
    }

    @Override
    protected ViewHodler createNewHolder() {
        return new ViewHodler();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_live_banner;
    }

    public void setList(List<BannerEntity> list) {
        this.list = list;
    }

    @Override
    public void bind(ViewHodler holder) {
        holder.banner
                .delayTime(5)
                .build(list);
    }

    static class ViewHodler extends EpoxyHolder {

        @BindView(R.id.item_live_banner)
        BannerView banner;

        @Override
        protected void bindView(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 12;
    }
}
