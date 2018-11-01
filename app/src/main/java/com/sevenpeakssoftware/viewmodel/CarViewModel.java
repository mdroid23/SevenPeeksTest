package com.sevenpeakssoftware.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sevenpeakssoftware.model.Car;

public class CarViewModel extends BaseObservable {
    private Context mContext;
    private Car car;

    public CarViewModel(Context mContext, Car car) {
        this.mContext = mContext;
        this.car = car;
    }

    @Bindable
    public String getTitle() {
        return car.getTitle();
    }

    @Bindable
    public int getId() {
        return car.getId();
    }

    @Bindable
    public String getIngress() {
        return car.getIngress();
    }

    @Bindable
    public String getDateTime() {
        return car.getDateTime();
    }


}
