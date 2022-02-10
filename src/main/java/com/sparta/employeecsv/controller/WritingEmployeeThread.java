package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.EmployeeDatabase;

public class WritingEmployeeThread extends Thread {

    private EmployeeDatabase eDatabase;

    public WritingEmployeeThread (EmployeeDatabase eDatabase){
        this.eDatabase = eDatabase;
    }

    @Override
    public void run() {
        eDatabase.insertRecordsSingleThread();
    }
}
