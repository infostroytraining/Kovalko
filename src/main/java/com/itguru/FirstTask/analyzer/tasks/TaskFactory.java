package com.itguru.FirstTask.analyzer.tasks;

import com.itguru.FirstTask.analyzer.Command;

public class TaskFactory {
    public static Command getInstance(String type) {
        if (type.toLowerCase().equals("frequency")) return new Frequency();
        else if (type.toLowerCase().equals("length")) return new Length();
        else if (type.toLowerCase().equals("duplicates")) return new Duplicates();
        return null;
    }
}