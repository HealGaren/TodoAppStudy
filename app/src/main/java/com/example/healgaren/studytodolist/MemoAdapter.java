package com.example.healgaren.studytodolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private List<Memo> memoList = new ArrayList<>();
    private OnItemClickListener onItemClickListener = null;

    public void add(Memo memo) {
        memoList.add(memo);
        notifyItemInserted(memoList.size() - 1);
    }

    public void addAll(Collection<Memo> memoCollection) {
        int oldSize = memoList.size();
        memoList.addAll(memoCollection);
        notifyItemRangeInserted(oldSize, memoCollection.size());
    }

    public void remove(int position) {
        memoList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int oldSize = memoList.size();
        memoList.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    public Memo get(int position) {
        return memoList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(memoList.get(position));
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView contentText;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            contentText = itemView.findViewById(R.id.text_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onClick(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onClickRemoveButton(getAdapterPosition());
                }
            });
        }

        void bind(Memo memo) {
            titleText.setText(memo.getTitle());
            contentText.setText(memo.getContent());
        }
    }

    public static interface OnItemClickListener {
        void onClick(int position);
        void onClickRemoveButton(int position);
    }
}
