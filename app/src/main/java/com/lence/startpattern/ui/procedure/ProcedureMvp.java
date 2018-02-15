package com.lence.startpattern.ui.procedure;

import com.lence.startpattern.model.ServicesModel;

import java.util.List;

public interface ProcedureMvp {

    void startDateSelection();

    void refreshList(List<ServicesModel> body);
}
