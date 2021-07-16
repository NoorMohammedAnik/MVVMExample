package com.app.mvvmexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.mvvmexample.adapter.HolidayAdapter;
import com.app.mvvmexample.databinding.ActivityMainBinding;
import com.app.mvvmexample.model.HolidayModel;
import com.app.mvvmexample.viewmodel.HolidayViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //https://medium.com/@kashifo/4-steps-to-mvvm-in-android-java-b05fb4148523


    final String TAG = getClass().getSimpleName();
    ActivityMainBinding binding;
    HolidayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUI();
        if (MyApplication.getInstance().isNetworkAvailable(MainActivity.this)) {
            binding.progressBar.setVisibility(View.VISIBLE);

            HolidayViewModel holidayViewModel = new HolidayViewModel();
            holidayViewModel.getHolidays().observe(this, new Observer<List<HolidayModel>>() {
                @Override
                public void onChanged(List<HolidayModel> currencyPojos) {
                    if (currencyPojos != null && !currencyPojos.isEmpty()) {
                        Log.e(TAG, "observe onChanged()=" + currencyPojos.size());
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.addHolidayList(currencyPojos);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        } else {
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }

    void initUI() {
        binding.rvHolidayList.setHasFixedSize(true);
        binding.rvHolidayList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HolidayAdapter();
        binding.rvHolidayList.setAdapter(adapter);
    }

}