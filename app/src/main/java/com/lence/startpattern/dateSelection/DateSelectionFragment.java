package com.lence.startpattern.dateSelection;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lence.startpattern.R;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;


public class DateSelectionFragment extends Fragment implements DateSelectionMvp {
    DateSelectionPresenter presenter;

    @BindView(R.id.status)
    TextView mStatus;
    @BindView(R.id.rv)
    RecyclerView rv;
    PickerAdapter adapter;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.time)
    CardView mTime;
    int mYear;
    int mMonth;
    int mDayOfMonth;

    RecyclerView mRvMorning;
    RecyclerView mRvDay;
    RecyclerView mRvEven;
    Context mContext;
    @BindView(R.id.freeTime)
    LinearLayout mFreeTime;
    @BindView(R.id.leftArrow)
    ImageView mLeftArrow;
    @BindView(R.id.rightArrow)
    ImageView mRightArrow;

    public DateSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_selection, container, false);
        mContext = view.getContext();
        presenter = new DateSelectionPresenter(this);
        ButterKnife.bind(this, view);
        Calendar today = Calendar.getInstance();
        Calendar future = Calendar.getInstance();
        future.setTimeInMillis(today.getTimeInMillis());
        future.add(Calendar.MONTH, 5);

        mCalendarView.setMinDate(today.getTimeInMillis());
        mCalendarView.setMaxDate(future.getTimeInMillis());

        Log.e("future", String.valueOf(future.get(Calendar.MONTH)));

        mRvMorning = view.findViewById(R.id.rvMorning);
        mRvDay = view.findViewById(R.id.rvDay);
        mRvEven = view.findViewById(R.id.rvEven);

//        Log.e("future", "future");
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(view.getContext(), PickerLayoutManager.HORIZONTAL, false);
//        pickerLayoutManager.setChangeAlpha(true);
//        pickerLayoutManager.setScaleDownBy(0.99f);
//        pickerLayoutManager.setScaleDownDistance(0.8f);

        adapter = new PickerAdapter(view.getContext(), presenter.getData(31), rv);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
        rv.setLayoutManager(pickerLayoutManager);
        rv.setAdapter(adapter);
// TODO: 22.01.2018 change count day of month in adapter

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDayOfMonth = dayOfMonth;

                if (year == 2018 && month == 0 && dayOfMonth == 24) {

                    rv.scrollToPosition(mDayOfMonth + 1);
                    mTime.setVisibility(View.VISIBLE);
                    mCalendarView.setVisibility(View.GONE);
                    mStatus.setVisibility(View.GONE);
                    mFreeTime.setVisibility(View.VISIBLE);
                    showFreeTime(mContext, year, month, dayOfMonth);
                } else {
                    mStatus.setText("Нет записи");


                }
            }
        });
        return view;
    }

    private void showFreeTime(Context context, int year, int month, int dayOfMonth) {
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRvDay.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRvEven.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRvMorning.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("1" + i + ":00");
        }

        TimesAdapter timesAdapter = new TimesAdapter(arrayList, presenter);
        mRvDay.setAdapter(timesAdapter);
        mRvEven.setAdapter(timesAdapter);
        mRvMorning.setAdapter(timesAdapter);

        mRvDay.getAdapter().notifyDataSetChanged();
        mRvEven.getAdapter().notifyDataSetChanged();
        mRvMorning.getAdapter().notifyDataSetChanged();

    }


    @OnClick(R.id.status)
    public void onViewClicked() {
//        int day = mDatePicker.getDayOfMonth();
//        int month = mDatePicker.getMonth();
//        int year = mDatePicker.getYear();
//        TimeSelectionFragment fragment = new TimeSelectionFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
//                .replace(R.id.content, fragment)
//                .addToBackStack("myStack")
//                .commit();


    }



    @OnClick(R.id.leftArrow)
    public void onMLeftArrowClicked() {
        // TODO: 22.01.2018 scroll 
    }

    @OnClick(R.id.rightArrow)
    public void onMRightArrowClicked() {
    }
}
