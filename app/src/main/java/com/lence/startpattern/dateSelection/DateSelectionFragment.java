package com.lence.startpattern.dateSelection;


import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
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

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.lence.startpattern.R;
import com.lence.startpattern.createRecord.CreateRecordFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @BindView(R.id.time)
    CardView mTime;


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
    @BindView(R.id.calendarView)
    com.applandeo.materialcalendarview.CalendarView mCalendarView;
    private Calendar thisDay;

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
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Выбор даты");
        Calendar today = Calendar.getInstance();
today.set(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DATE)-1);
        Calendar future = Calendar.getInstance();
        future.set(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+5,31);


        mCalendarView.setMinimumDate(today);
        mCalendarView.setMaximumDate(future);
        List<Calendar> calendars = new ArrayList<>();
        Calendar disableDay = Calendar.getInstance();
        disableDay.set(2018,0,28);
        calendars.add(disableDay);
        mCalendarView.setDisabledDays(calendars);

        Log.e("future", String.valueOf(future.get(Calendar.MONTH)));

        mRvMorning = view.findViewById(R.id.rvMorning);
        mRvDay = view.findViewById(R.id.rvDay);
        mRvEven = view.findViewById(R.id.rvEven);

//        Log.e("future", "future");
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(view.getContext(), PickerLayoutManager.HORIZONTAL, false);
//        pickerLayoutManager.setChangeAlpha(false);
//        pickerLayoutManager.setScaleDownBy(0.99f);
//        pickerLayoutManager.setScaleDownDistance(0.6f);

        adapter = new PickerAdapter(view.getContext(), presenter.getData(31), rv);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
        rv.setLayoutManager(pickerLayoutManager);
        rv.setAdapter(adapter);
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
// TODO: 22.01.2018 change count day of month in adapter

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                thisDay = eventDay.getCalendar();

                if (thisDay.get(Calendar.YEAR) == 2018 && thisDay.get(Calendar.MONTH) == 0 && thisDay.get(Calendar.DATE) == 24) {

                    rv.scrollToPosition(thisDay.get(Calendar.DATE));
                    rv.scrollBy(62, 0);
                    mTime.setVisibility(View.VISIBLE);
                    mCalendarView.setVisibility(View.GONE);
                    mStatus.setVisibility(View.GONE);
                    mFreeTime.setVisibility(View.VISIBLE);
                    showFreeTime(mContext, thisDay);
                } else {
                    mStatus.setText("Нет записи");


                }
            }

        });
        return view;
    }

    @Override
    public void showFreeTime(Context context, Calendar thisDay) {
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

    @Override
    public void startOnlineRecord() {
        CreateRecordFragment fragment = new CreateRecordFragment();
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
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


    @Override
    public void showCalendar() {
        mTime.setVisibility(View.GONE);
        mCalendarView.setVisibility(View.VISIBLE);
        mStatus.setVisibility(View.VISIBLE);
        mFreeTime.setVisibility(View.GONE);
    }


}
