package com.chen.viewpage2;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public ImageAdapter(Context context) {
        super(R.layout.item_image);
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        Glide.with(context)
                .load(item)
                .into((ImageView) helper.getView(R.id.img));
    }
}