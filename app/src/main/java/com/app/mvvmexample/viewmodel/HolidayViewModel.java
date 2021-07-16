package com.app.mvvmexample.viewmodel;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.mvvmexample.HolidayRepo;
import com.app.mvvmexample.model.HolidayModel;

public class HolidayViewModel {
    private HolidayRepo holidayRepo;
    private MutableLiveData<List<HolidayModel>> mutableLiveData;

    public HolidayViewModel(){
        holidayRepo = new HolidayRepo();
    }

    public LiveData<List<HolidayModel>> getHolidays() {
        if(mutableLiveData==null){
            mutableLiveData = holidayRepo.requestHolidays();
        }
        return mutableLiveData;
    }

}
