package com.lence.startpattern.ui.doctor.review;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.lence.startpattern.R;
import com.lence.startpattern.api.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateReviewDialog extends DialogFragment {

    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.text)
    EditText mText;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.button)
    Button mButton;
    Unbinder unbinder;
    private int mDoctorId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_review_dialog, container, false);
        getDialog().setTitle("оставьте отзыв");
        View title = getDialog().findViewById(android.R.id.title);
        title.setBackgroundColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mDoctorId = bundle.getInt("doctorId");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if(!mName.getText().toString().isEmpty()&&!mText.getText().toString().isEmpty()&&mCheckbox.isChecked()) {
            App.getApi().postReview(mName.getText().toString(), mText.getText().toString(), mRatingBar.getNumStars(), mDoctorId)
                    .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("CreateReviewDialog", response.code()+" "+response.message());
                    if (response.code()==200){
                        Toast.makeText(getActivity(),"Ваш отзыв принят",Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(getActivity(),"Заполните все поля",Toast.LENGTH_SHORT).show();
        }
    }
}
