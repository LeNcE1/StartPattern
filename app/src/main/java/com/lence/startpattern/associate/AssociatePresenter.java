package com.lence.startpattern.associate;

public class AssociatePresenter {
    AssociateMvp mMvp;

    public AssociatePresenter(AssociateMvp mvp) {
        mMvp = mvp;
    }

    void startProcedure() {
        mMvp.startProcedure();
    }
}
