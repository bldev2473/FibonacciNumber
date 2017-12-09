package com.fibonaccinumber.bldev;

/**
 * Created by bldev on 2017. 12. 9..
 */

public interface TestContract {
    interface View {

    }

    interface Presenter {
        //Data : View <-> Model
        int inputTestNumberToModel(int inputNum);

        StringBuilder insertResultToModelDB(int inputNum, int outputNum);

        void resetViewDB();

        //OutputNumber Overflow
        String sendErrorMsgToView();
    }
}

