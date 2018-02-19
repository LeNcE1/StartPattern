package com.lence.startpattern.ui.associateAll;


import com.lence.startpattern.model.AssociateModel;

import java.util.List;

public interface AssociateAllMvp {
    void startDoctor(int id,String name, String spec, String image);

    void refreshList(List<AssociateModel> body);
}
