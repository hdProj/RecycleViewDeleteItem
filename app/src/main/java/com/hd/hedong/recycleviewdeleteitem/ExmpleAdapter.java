package com.hd.hedong.recycleviewdeleteitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dong.he on 2016/9/22.
 */
public class ExmpleAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> list;
    public boolean isShow = false;
    private List<Boolean> listCheck;

    public ExmpleAdapter(Context context, List<String> list, List<Boolean> listCheck) {
        mContext = context;
        this.list = list;
        this.listCheck = listCheck;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new CarViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CarViewHolder viewHolder = (CarViewHolder) holder;
        viewHolder.tvTitle.setText(list.get(position));
        if (isShow) {
            viewHolder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_checkbox)
        ImageView ivDelete;

        @BindView(R.id.item_id)
        TextView tvTitle;
        OnItemClickListener onItemClickListener;

        public CarViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                if (isShow) {
                    onItemClickListener.setDeleteListener(v, getLayoutPosition());
                } else {
                    onItemClickListener.setOnItemClickListener(v, getLayoutPosition());
                }
            }
        }
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);

        void setDeleteListener(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
