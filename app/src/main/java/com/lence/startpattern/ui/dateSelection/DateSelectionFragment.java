package com.lence.startpattern.ui.dateSelection;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.lence.startpattern.R;
import com.lence.startpattern.model.DateMap;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;
import com.lence.startpattern.utils.ChangeStyle;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager;


public class DateSelectionFragment extends Fragment implements DateSelectionMvp {
    DateSelectionPresenter presenter;

    @BindView(R.id.rv)
    RecyclerView rv;
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
    private Calendar thisDay;
    ArrayList<DateMap> dates;
    ArrayList<String> keys;
    ArrayList<Calendar> disableDays;

    public DateSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DateSelectionPresenter(this);
        presenter.loadTimeDoctor(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_selection, container, false);

        ChangeStyle.whiteColor(getActivity());

        mContext = view.getContext();

        ButterKnife.bind(this, view);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Выбор даты");
        Calendar today = Calendar.getInstance();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE) - 1);
        Calendar future = Calendar.getInstance();
        future.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 5, 31);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
// TODO: 06.03.2018 разобраться почему календарь начинается с января
        mCalendarView.setMinimumDate(today);
        mCalendarView.setMaximumDate(future);

//        Calendar disableDay = Calendar.getInstance();
//        disableDay.set(2018, 1, 1);
//        calendars.add(disableDay);
//        mCalendarView.setDisabledDays(calendars);

        Log.e("future", String.valueOf(future.get(Calendar.MONTH)));

//        Log.e("future", "future");

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
                Calendar today = Calendar.getInstance();
                if (disableDays.indexOf(thisDay) == -1&&!thisDay.before(today)) {
                    rv.scrollToPosition(thisDay.get(Calendar.DATE));
                    rv.scrollBy(62, 0);
                    mTime.setVisibility(View.VISIBLE);
                    mNext.setVisibility(View.VISIBLE);

                    showFreeTime(mContext, thisDay);
                }
            }

        });
        return view;
    }

    @Override
    public void showFreeTime(Context context, final Calendar thisDay) {
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        //Log.e("map", dates.get(5).getKey());

        int index = keys.indexOf(thisDay.get(Calendar.YEAR) + "-"
                + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
                + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)));
        Log.e("index", thisDay.get(Calendar.YEAR) + "-"
                + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
                + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)));
        Log.e("index", index + "");
        ArrayList<String> arrayList = dates.get(index).getValue();
        mNext.setText("Записаться "
                + (thisDay.get(Calendar.YEAR) + "-"
                + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
                + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)))
                + " на " + (arrayList.get(0).equals("00:00")?arrayList.get(1):arrayList.get(0)));
        adapter = new PickerAdapter(getContext(), arrayList, rv);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getContext(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @Override
            public void selectedView(View view) {
                TextView text = view.findViewById(R.id.picker_item);
                //Log.e("text",text.getText().toString());
                mNext.setText("Записаться "
                        + (thisDay.get(Calendar.YEAR) + "-"
                        + ((thisDay.get(Calendar.MONTH) + 1) < 10 ? ("0" + (thisDay.get(Calendar.MONTH) + 1)) : (thisDay.get(Calendar.MONTH) + 1)) + "-"
                        + (thisDay.get(Calendar.DATE) < 10 ? ("0" + thisDay.get(Calendar.DATE)) : thisDay.get(Calendar.DATE)))
                        + " на " + text.getText().toString());
            }
        });
//        pickerLayoutManager.setChangeAlpha(false);
//        pickerLayoutManager.setScaleDownBy(0.99f);
//        pickerLayoutManager.setScaleDownDistance(0.6f);

        rv.setLayoutManager(pickerLayoutManager);
        rv.setAdapter(adapter);

    }

    @Override
    public void startOnlineRecord() {
        SelectionScreenFragment fragment = new SelectionScreenFragment();
        // TODO: 29.01.2018 add date bundle 
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @Override
    public void setDates(ArrayList<DateMap> map) {
        //Log.e("map", map.get(5).getKey());
        dates = map;
        keys = new ArrayList<>();
        disableDays = new ArrayList<>();

        for (DateMap d : dates) {
            keys.add(d.getKey());
            if (d.getValue().isEmpty()) {
                String[] s = d.getKey().split("-");
                Log.e("cal", s[0] + " " + s[1] + " " + s[2]);
                Calendar c = Calendar.getInstance();
                c.set(Integer.valueOf(s[0]), (Integer.valueOf(s[1]) - 1), Integer.valueOf(s[2]));
                Log.e("cal", String.valueOf(c.get(Calendar.MONTH)));
                disableDays.add(c);
            }

        }
        mCalendarView.setDisabledDays(disableDays);
    }


    //@OnClick(R.id.status)
    // public void onViewClicked() {
//        int day = mDatePicker.getDayOfMonth();
//        int month = mDatePicker.getMonth();
//        int year = mDatePicker.getYear();
//        TimeSelectionFragment fragment = new TimeSelectionFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
//                .replace(R.id.content, fragment)
//                .addToBackStack("myStack")
//                .commit();


    //  }


    @OnClick(R.id.leftArrow)
    public void onMLeftArrowClicked() {

    }

    @OnClick(R.id.rightArrow)
    public void onMRightArrowClicked() {
    }


    @Override
    public void showCalendar() {
        mTime.setVisibility(View.GONE);
        mCalendarView.setVisibility(View.VISIBLE);
        mNext.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.next)
    public void onViewClicked() {
    }
}
