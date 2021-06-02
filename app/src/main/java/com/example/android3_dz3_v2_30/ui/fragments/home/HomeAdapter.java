package com.example.android3_dz3_v2_30.ui.fragments.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_dz3_v2_30.databinding.ItemPublishBinding;
import com.example.android3_dz3_v2_30.domain.model.Publish;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.PublishVH> {

    private List<Publish> list = new ArrayList<>();
    private PublishListener listener;

    public void setPublishListener(PublishListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PublishVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPublishBinding ui = ItemPublishBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PublishVH(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull PublishVH holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addListPublish(List<Publish> body) {
        if (body != null) {
            list.clear();
            list.addAll(body);
            notifyDataSetChanged();
        }
    }

    public void deletePublish(Publish body, int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    public class PublishVH extends RecyclerView.ViewHolder {
        private ItemPublishBinding ui;

        public PublishVH(ItemPublishBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
        }

        public void onBind(Publish publish) {
            ui.tvContent.setText(publish.getContent());
            ui.tvTitle.setText(publish.getTitle());
            ui.tvGroup.setText(String.valueOf(publish.getGroupID()));
            ui.tvUser.setText(String.valueOf(publish.getUserID()));
            ui.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(publish, getAdapterPosition());
                    return true;
                }
            });
            ui.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(publish, getAdapterPosition());
                }
            });
        }
    }
}
