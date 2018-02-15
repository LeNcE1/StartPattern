package com.lence.startpattern.ui.associate;


import com.lence.startpattern.model.AssociateModel;

import java.util.List;

public interface AssociateMvp {
    void startProcedure();

    void refreshList(List<AssociateModel> body);
}
