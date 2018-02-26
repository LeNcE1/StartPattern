package com.lence.startpattern.ui.procedure;

import java.util.List;

public interface ProcedureMvp {

    void startDateSelection();

    void refreshList(List<Object> body);

    //void refreshListServicesModel(List<Object> body);

   // void refreshListDoctorServicesModel(List<AssociateServicesModel> body);
}
