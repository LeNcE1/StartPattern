package com.lence.startpattern.ui.procedure;



public class ProcedurePresenter {
    ProcedureMvp mMvp;

    public ProcedurePresenter(ProcedureMvp mvp) {
        mMvp = mvp;
    }

    public void startProcedure() {
        mMvp.startDateSelection();
    }
}
