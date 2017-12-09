package com.fibonaccinumber.bldev;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by bldev on 2017. 12. 9..
 */

public class TestModel {

    /*
    //Recursive Fibonacci Method
    public int fib(int n){
        if (n < 2)
            return n;
        else
            return fib(n-1) + fib(n-2);
    }
    */

    DBHelper mDB;
    TestPresenter presenter;

    public TestModel(DBHelper mDB) {
        this.mDB = mDB;
    }

    //Logic : Lambda Expression For Fibonacci Number
    Function calFib = (int n) -> {
        int i = 0, j = 1, k = 0;
        if (n == 0 || n == 1) {
            return n;
        } else {
            for (int l = 1; l < n; l++) {
                k = i + j;
                i = j;
                j = k;
            }
            if (k > 0)
                return k;
            else {
                //OutputNumber Overflow
                msgForView = "Overflow";
                sendMsgToView();
                return 0;
            }
        }
    };

    public int fib(int n) {
        int result;
        result = calFib.func(n);
        return result;
    }

    public StringBuilder sendResultToView(int inputNum, int outputNum) {
        //Insert the Result of the Fibonacci Number to the DB of the DBHelper Class
        mDB.insertResult(inputNum, outputNum);

        //Send the Result of the DB to the TextView of the View
        TreeMap<Integer, Integer> tm = new TreeMap<>(mDB.getAllResults());
        Iterator<Integer> iteratorKey = tm.keySet().iterator();
        StringBuilder resultTemp = new StringBuilder();
        while (iteratorKey.hasNext()) {
            Integer key = iteratorKey.next();
            resultTemp.append(String.valueOf(key)).append(" : ").append(String.valueOf(tm.get(key))).append("\n");
        }

        return resultTemp;
    }

    public void deleteAllRowsForView() {
        mDB.deleteAllRows();
    }

    private String msgForView = "";

    public String sendMsgToView() {
        return msgForView;
    }
}
