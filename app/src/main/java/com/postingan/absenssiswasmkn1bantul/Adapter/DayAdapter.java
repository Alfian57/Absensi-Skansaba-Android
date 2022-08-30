package com.postingan.absenssiswasmkn1bantul.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.postingan.absenssiswasmkn1bantul.R;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> data;
    Context context;
    OnItemClickListener listener;

    public DayAdapter(Context context, List<String> data, OnItemClickListener listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder instanceof DayViewHolder){
            final DayViewHolder dayViewHolder = (DayViewHolder) holder;
            dayViewHolder.dayName.setText(data.get(position));
            dayViewHolder.dayContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDetailClick(position);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onDetailClick(int position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class DayViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener {
        TextView dayName;
        LinearLayout dayContainer;
        public DayViewHolder(View view) {
            super(view);

            dayName = view.findViewById(R.id.textDayName);
            dayContainer = view.findViewById(R.id.dayContainer);
        }

        @Override
        public void onDetailClick(int position) {

        }
    }
}
