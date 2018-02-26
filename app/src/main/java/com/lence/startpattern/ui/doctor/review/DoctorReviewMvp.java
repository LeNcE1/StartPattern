package com.lence.startpattern.ui.doctor.review;


import com.lence.startpattern.model.DoctorReviewsModel;

import java.util.List;

public interface DoctorReviewMvp {
    void refreshList(List<DoctorReviewsModel> body);
}
