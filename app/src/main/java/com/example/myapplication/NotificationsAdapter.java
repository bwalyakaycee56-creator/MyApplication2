package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NotificationItem item);
    }

    private List<NotificationItem> items;
    private final OnItemClickListener listener;

    public NotificationsAdapter(List<NotificationItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void updateItems(List<NotificationItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvSubtitle.setText(item.getSubtitle());
        holder.tvTime.setText(item.getTime());
        holder.dotUnread.setVisibility(item.isUnread() ? View.VISIBLE : View.INVISIBLE);

        int iconRes;
        int color;
        switch (item.getType()) {
            case REGISTRATION:
                iconRes = R.drawable.ic_check;
                color = Color.parseColor("#2E7D32");
                break;
            case GROUP_MEETING:
                iconRes = R.drawable.ic_calendar;
                color = Color.parseColor("#F57C00");
                break;
            case SYSTEM:
                iconRes = R.drawable.ic_info;
                color = Color.parseColor("#1976D2");
                break;
            case GROUP_CHANGE:
                iconRes = R.drawable.ic_person;
                color = Color.parseColor("#7B1FA2");
                break;
            case ANNOUNCEMENT:
                iconRes = R.drawable.ic_campaign;
                color = Color.parseColor("#C62828");
                break;
            case GROUP_FULL:
            default:
                iconRes = R.drawable.ic_group_full;
                color = Color.parseColor("#D32F2F");
                break;
        }

        holder.ivIcon.setImageResource(iconRes);
        Drawable bg = holder.ivIconBg.getDrawable().mutate();
        bg.setTint(color);
        holder.ivIconBg.setImageDrawable(bg);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconBg, ivIcon;
        TextView tvTitle, tvSubtitle, tvTime;
        View dotUnread;

        NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIconBg = itemView.findViewById(R.id.ivIconBg);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            dotUnread = itemView.findViewById(R.id.dotUnread);
        }
    }
}