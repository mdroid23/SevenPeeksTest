package com.sevenpeakssoftware.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sevenpeakssoftware.mahesh.R;
import com.sevenpeakssoftware.mahesh.databinding.RowItemCarsBinding;
import com.sevenpeakssoftware.model.Car;
import com.sevenpeakssoftware.viewmodel.CarViewModel;

import java.util.ArrayList;

public class CarsAdapter extends  RecyclerView.Adapter<CarsAdapter.BindingHolder>{

    private ArrayList<Car> carArrayList;
    private Context mContext;

    public CarsAdapter(Context mContext, ArrayList<Car> cars) {
        this.mContext = mContext;
        this.carArrayList = cars;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowItemCarsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_item_cars, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        RowItemCarsBinding binding = holder.binding;
        Car car = carArrayList.get(holder.getAdapterPosition());
        CarViewModel carViewModel = new CarViewModel(mContext, car);
        binding.setCvm(carViewModel);
        Glide.with(mContext).load(car.getImage()).centerCrop().into(binding.imageView);
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private RowItemCarsBinding binding;

        public BindingHolder(RowItemCarsBinding binding) {
            super(binding.rowItemCar);
            this.binding = binding;
        }
    }
}
