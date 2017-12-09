package com.fibonaccinumber.bldev;

/**
 * Created by bldev on 2017. 12. 9..
 */

public class TestPresenter implements TestContract.Presenter {
    TestContract.View view;
    TestModel model;

    public TestPresenter(TestContract.View tv, DBHelper mDB) {
        this.view = tv;
        model = new TestModel(mDB);
    }

    //Data : Presenter <-> Model
    @Override
    public int inputTestNumberToModel(int inputNum) {
        return model.fib(inputNum);
    }

    @Override
    public StringBuilder insertResultToModelDB(int inputNum, int outputNum) {
        return model.sendResultToView(inputNum, outputNum);
    }

    @Override
    public void resetViewDB() {
        model.deleteAllRowsForView();
    }

    @Override
    public String sendErrorMsgToView() {
        return model.sendMsgToView();
    }
}


