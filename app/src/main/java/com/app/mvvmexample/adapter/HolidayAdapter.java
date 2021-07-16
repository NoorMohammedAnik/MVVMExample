package com.app.mvvmexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mvvmexample.R;
import com.app.mvvmexample.databinding.ItemHolidayJavaBinding;
import com.app.mvvmexample.model.HolidayModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {

    public List<HolidayModel> holidayList;

    public HolidayAdapter() {
        holidayList = new ArrayList<>();
    }

    public void addHolidayList(List<HolidayModel> currencyList) {
        this.holidayList = currencyList;
    }

    @Override
    public int getItemCount() {
        return holidayList != null ? holidayList.size() : 0;
    }


    @Override
    public @NotNull MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemHolidayJavaBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_holiday_java, parent, false);

        return new MyViewHolder(binding);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemHolidayJavaBinding binding;

        MyViewHolder(ItemHolidayJavaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(view.getContext(), holidayList.get(getAbsoluteAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setModel(holidayList.get(position));
    }
}
