package com.hotbitmapgg.bilibili.module.home.live.model;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.bilibili.entity.live.LiveAppIndexInfo.DataBean.PartitionsBean.PartitionBean;
import com.hotbitmapgg.ohmybilibili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sword on 2017/4/6.
 */

public class PartitionModel extends EpoxyModelWithHolder<PartitionModel.LivePartitionViewHolder> {

    PartitionBean partition;

    @Override
    protected LivePartitionViewHolder createNewHolder() {
        return new LivePartitionViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_live_partition_title;
    }

    public PartitionModel(PartitionBean partition) {
        this.partition = partition;
    }

    @Override
    public void bind(LivePartitionViewHolder holder) {

        Glide.with(holder.itemIcon.getContext())
                .load(partition.getSub_icon().getSrc())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemIcon);

        holder.itemTitle.setText(partition.getName());
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(
                "当前" + partition.getCount() + "个直播");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                holder.itemIcon.getContext().getResources().getColor(R.color.pink_text_color));
        stringBuilder.setSpan(foregroundColorSpan, 2,
                stringBuilder.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.itemCount.setText(stringBuilder);
    }

    public void setPartition(PartitionBean partition) {
        this.partition = partition;
    }

    /**
     * 直播界面分区类型 ViewHolder
     */
    static class LivePartitionViewHolder extends EpoxyHolder {

        @BindView(R.id.item_live_partition_icon)
        ImageView itemIcon;

        @BindView(R.id.item_live_partition_title)
        TextView itemTitle;

        @BindView(R.id.item_live_partition_count)
        TextView itemCount;

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
