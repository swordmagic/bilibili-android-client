package com.hotbitmapgg.bilibili.module.home.live.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.ohmybilibili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sword on 2017/4/6.
 */

public class LiveEntranceModel extends EpoxyModelWithHolder<LiveEntranceModel.LiveEntranceViewHolder> {

    private int[] entranceIconRes = new int[]{
            R.drawable.live_home_follow_anchor,
            R.drawable.live_home_live_center,
            R.drawable.live_home_search_room,
            R.drawable.live_home_all_category
    };

    private String[] entranceTitles = new String[]{
            "关注主播", "直播中心",
            "搜索直播", "全部分类"
    };

    final int position;

    public LiveEntranceModel(int position) {
        this.position = position;
    }


    @Override
    protected LiveEntranceViewHolder createNewHolder() {
        return new LiveEntranceViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_live_entrance;
    }

    @Override
    public void bind(LiveEntranceViewHolder holder) {

        holder.title.setText(entranceTitles[position]);

        Glide.with(holder.image.getContext())
                .load(entranceIconRes[position])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
    }

    /**
     * 直播界面Item分类 ViewHolder
     */
    static class LiveEntranceViewHolder extends EpoxyHolder {

        @BindView(R.id.live_entrance_title)
        public TextView title;

        @BindView(R.id.live_entrance_image)
        public ImageView image;

        @Override
        protected void bindView(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 3;
    }
}
