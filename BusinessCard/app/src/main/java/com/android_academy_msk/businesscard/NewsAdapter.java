package com.android_academy_msk.businesscard;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;
    private final OnItemClickListener clickListener;

    public NewsAdapter(List<NewsItem> news, Context context, OnItemClickListener clickListener) {
        this.news = news;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView ivNewsPic;
        public final TextView tvCategory;
        public final TextView tvTitle;
        public final TextView tvDesc;
        public final TextView tvDate;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(news.get(position));
                }
            });
            ivNewsPic = itemView.findViewById(R.id.ivNewsPic);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.news_list_item, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem new_item = news.get(position);
        bindAll(new_item, holder);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener{
        void onItemClick(NewsItem news_item);
    }

    private void bindAll(NewsItem new_item, ViewHolder holder){
        holder.tvCategory.setText(new_item.getCategory().getName());
        holder.tvTitle.setText(new_item.getTitle());
        holder.tvDesc.setText(new_item.getPreviewText());
        holder.tvDate.setText(new_item.getNormalDate());
        Glide.with(context).load(new_item.getImageUrl()).into(holder.ivNewsPic);
    }
}
