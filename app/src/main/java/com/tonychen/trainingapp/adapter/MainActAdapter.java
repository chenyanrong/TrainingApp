package com.tonychen.trainingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.model.ItemMainActBean;

import java.util.List;


/**
 * Created by chenc on 2017/8/1.
 */

public class MainActAdapter extends RecyclerView.Adapter<MainActAdapter.ItemMainViewHolder> {
    private List<ItemMainActBean> mData;

    public MainActAdapter(List<ItemMainActBean> mData) {
        this.mData = mData;
        if (mData == null || mData.size() == 0) {
            throw new RuntimeException("Adapter mdata can not be null!");
        }
    }

    @Override
    public ItemMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMainViewHolder viewHolder = new ItemMainViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_act_main, parent, false)
        );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemMainViewHolder holder, final int position) {
        holder.tvTitle.setText(position + " : " + mData.get(position).getTitle());
        holder.tvIntro.setText(mData.get(position).getIntro());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemonClickListener != null) {
                    onItemonClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemonClickListener(OnItemonClickListener onItemonClickListener) {
        this.onItemonClickListener = onItemonClickListener;
    }

    private OnItemonClickListener onItemonClickListener;

    public interface OnItemonClickListener {
        void onItemClick(View view, int postion);
    }

    static class ItemMainViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvIntro;
        LinearLayout llContainer;

        public ItemMainViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvIntro = (TextView) itemView.findViewById(R.id.tv_intro);
            llContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
        }
    }
}
