package com.hotbitmapgg.bilibili.module.home.live.model;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.bilibili.entity.live.LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean;
import com.hotbitmapgg.bilibili.widget.CircleImageView;
import com.hotbitmapgg.ohmybilibili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sword on 2017/4/6.
 */

public class LiveItemModel extends EpoxyModelWithHolder<LiveItemModel.LiveItemViewHolder> {


    private LivesBean livesBean;

    @Override
    protected LiveItemViewHolder createNewHolder() {
        return new LiveItemViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_live_partition;
    }

    public void setLivesBean(LivesBean livesBean) {
        this.livesBean = livesBean;
    }

    public LiveItemModel(LivesBean livesBean) {
        this.livesBean = livesBean;
    }

    @Override
    public void bind(LiveItemViewHolder holder) {

        Glide.with(holder.itemLiveCover.getContext())
                .load(livesBean.getCover().getSrc())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.itemLiveCover);

        Glide.with(holder.itemLiveCover.getContext())
                .load(livesBean.getCover().getSrc())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ico_user_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemLiveUserCover);

        holder.itemLiveTitle.setText(livesBean.getTitle());
        holder.itemLiveUser.setText(livesBean.getOwner().getName());
        holder.itemLiveCount.setText(String.valueOf(livesBean.getOnline()));

//        holder.itemLiveLayout.setOnClickListener(v -> LivePlayerActivity.
//                launch(holder.itemLiveCover.getContext(), livesBean.getRoom_id(),
//                        livesBean.getTitle(), livesBean.getOnline(), livesBean.getOwner().getFace(),
//                        livesBean.getOwner().getName(), livesBean.getOwner().getMid()));

    }

    /**
     * 直播界面Grid Item ViewHolder
     */
    static class LiveItemViewHolder extends EpoxyHolder {

        @BindView(R.id.item_live_cover)
        ImageView itemLiveCover;

        @BindView(R.id.item_live_user)
        TextView itemLiveUser;

        @BindView(R.id.item_live_title)
        TextView itemLiveTitle;

        @BindView(R.id.item_live_user_cover)
        CircleImageView itemLiveUserCover;

        @BindView(R.id.item_live_count)
        TextView itemLiveCount;

        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;


        @Override
        protected void bindView(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 6;
    }
}
