package com.lence.startpattern.ui.dateSelection;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.lence.startpattern.ui.EntryActivity;
import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.model.DateMap;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;


public class DateSelectionActivity extends AppCompatActivity implements DateSelectionMvp {
    DateSelectionPresenter mPresenter;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    PickerAdapter adapter;
    @BindView(R.id.time)
    CardView mTime;
    Context mContext;
    @BindView(R.id.leftArrow)
    ImageView mLeftArrow;
    @BindView(R.id.rightArrow)
    ImageView mRightArrow;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.next)
    Button mNext;
    TextView label;
    private Calendar thisDay;
    ArrayList<DateMap> dates;
    ArrayList<String> keys;
    ArrayList<Calendar> disableDays;
    PickerLayoutManager pickerLayoutManager;
    ArrayList<String> arrayList;
    String date;
    String time;
    ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DateSelectionPresenter(this);
        mPresenter.loadTimeDoctor(SingletonStorage.getInstance().getAssociateId());
        mDialog = new ProgressDialog(this, R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dialog_progress);
                getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);
            }
        };

        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.date_selection);
        mContext = this;

        ButterKnife.bind(this);
        label = (TextView) findViewById(R.id.label);
        label.setText("ВЫБОР ДАТЫ");

        Calendar today = Calendar.getInstance();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), (today.get(Calendar.DATE)) - 1);
        Calendar future = Calendar.getInstance();
        future.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 5, 31);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
//        Log.e("today", String.valueOf(today.get(Calendar.YEAR))
//                + "-" + String.valueOf(today.get(Calendar.MONTH))
//                + "-" + String.valueOf(today.get(Calendar.DATE)));
        mCalendarView.setMinimumDate(today);
        mCalendarView.setMaximumDate(future);


        //Log.e("future", String.valueOf(future.get(Calendar.MONTH)));

//        Log.e("future", "future");

// TODO: 22.01.2018 change count day of month in adapter

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                thisDay = eventDay.getCalendar();
                Calendar today = Calendar.getInstance();
                if (disableDays.indexOf(thisDay) == -1 && !thisDay.before(today)) {
                    mRecyclerView.scrollToPosition(thisDay.get(Calendar.DATE));
                    mRecyclerView.scrollBy(120, 0);
                    mTime.setVisibility(View.VISIBLE);
                    mNext.setVisibility(View.VISIBLE);

                    showFreeTime(mContext, thisDay);
                }
            }

        });

    }


    @Override
    public void showFreeTime(Context context, final Calendar thisDay) {
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        //Log.e("map", dates.get(5).getKey());

        int index = keys.indexOf(thisDay.get(Calendar.YEAR) + "-"
                + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
                + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)));
//        Log.e("index", thisDay.get(Calendar.YEAR) + "-"
//                + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
//                + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)));
        //Log.e("index", index + "");
        arrayList = dates.get(index).getValue();
        date = ((thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE))
                + "-" + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1))
                + "-" + thisDay.get(Calendar.YEAR));
        time = arrayList.get(0).equals("00:00") ? arrayList.get(1) : arrayList.get(0);
        label.setText(date + " " + time);
        adapter = new PickerAdapter(this, arrayList, mRecyclerView);
        pickerLayoutManager = new PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false);

        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @Override
            public void selectedView(View view) {
                TextView text = view.findViewById(R.id.picker_item);
                //Log.e("text",text.getText().toString());
//                Log.e("Last", String.valueOf(pickerLayoutManager.findLastVisibleItemPosition()));
//                Log.e("First", String.valueOf(pickerLayoutManager.findFirstVisibleItemPosition()));
//                Log.e("this", String.valueOf(arrayList.get(pickerLayoutManager.findLastCompletelyVisibleItemPosition())));
                time = text.getText().toString();
                label.setText(date + " " + time);
            }
        });

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!arrayList.get(pickerLayoutManager.findFirstCompletelyVisibleItemPosition()).equals("00:00")) {
                    mRecyclerView.scrollToPosition(pickerLayoutManager.findFirstVisibleItemPosition());
                    mRecyclerView.scrollBy(-184, 0);
                    time = arrayList.get(pickerLayoutManager.findLastCompletelyVisibleItemPosition());
                    label.setText(date + " " + time);

                }
            }
        });

        mRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!arrayList.get(pickerLayoutManager.findLastCompletelyVisibleItemPosition()).equals("00:00")) {
                    mRecyclerView.scrollToPosition(pickerLayoutManager.findLastVisibleItemPosition());
                    mRecyclerView.scrollBy(184, 0);
                    time = arrayList.get(pickerLayoutManager.findFirstCompletelyVisibleItemPosition());
                    label.setText(date + " " + time);
                }
            }
        });
//        pickerLayoutManager.setChangeAlpha(false);
//        pickerLayoutManager.setScaleDownBy(0.99f);
//        pickerLayoutManager.setScaleDownDistance(0.6f);

        mRecyclerView.setLayoutManager(pickerLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    public void setDates(ArrayList<DateMap> map) {
        //Log.e("map", map.get(5).getKey());
        dates = map;
        keys = new ArrayList<>();
        disableDays = new ArrayList<>();

        for (DateMap date : dates) {
            keys.add(date.getKey());
            if (date.getValue().isEmpty()) {
                String[] split = date.getKey().split("-");
                //Log.e("cal", s[0] + " " + s[1] + " " + s[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.valueOf(split[0]), (Integer.valueOf(split[1]) - 1), Integer.valueOf(split[2]));
                // Log.e("cal", String.valueOf(c.get(Calendar.MONTH)));
                disableDays.add(calendar);
            }

        }
        mCalendarView.setDisabledDays(disableDays);
        mCalendarView.showCurrentMonthPage();
        showFreeTime(mContext, Calendar.getInstance());
        mDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.next)
    public void onViewClicked() {
        SingletonStorage.getInstance().setDate(date);
        SingletonStorage.getInstance().setTime(time);
        startActivity(new Intent(this, EntryActivity.class));
    }
}
