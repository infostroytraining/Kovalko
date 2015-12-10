package com.itguru.FirstTask;

import com.itguru.FirstTask.analyzer.Command;
import com.itguru.FirstTask.analyzer.tasks.TaskFactory;
import com.itguru.FirstTask.analyzer.utils.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = null;
        while (true) {
            try {
                System.out.println("Enter command. For help enter --help -a");
                String commandLine = reader.readLine();
                Map<String, String> commands = commands(commandLine);
                for (Map.Entry entry : commands.entrySet()) {
                    String key = entry.getKey().toString().trim().toLowerCase();
                    String value = entry.getValue().toString().trim().toLowerCase();
                    if (key.equals("-i") || key.equals("--input")) {
                        text = null;
                        try {
                            text = TextUtils.getContentFromFile(value);
                        } catch (IOException ex) {
                            System.out.println("Incorrect path!");
                        }
                    }
                    else if ((key.equals("-t") || key.equals("--task")) && text != null) {
                        String task = value;
                        Command command = TaskFactory.getInstance(task);
                        if (command != null) command.execute(text);
                    }
                    else if (key.equals("--help")) help();
                    else if (text == null) System.out.println("You must enter file path!");
                    else System.out.println("Incorrect command! Try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void help() {
        String instruction = "-i (--input) - path to the input file (e.g. C:\\Program Files\\Java\\input.txt). Type: String, Required: true\n" +
                "-t (--task) – task to execute. Type: Enum, Required: true, Permitted values: frequency, length, duplicates\n" +
                "-- help –a detailed information of how to use app";
        System.out.println(instruction);
    }

    private static Map<String, String> commands(String text) {
        String[] commands = text.split(" ");
        Map<String, String> command = new LinkedHashMap<String, String>();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("-t") || commands[i].equals("--task") ||
                    commands[i].equals("-i") || commands[i].equals("--input") ||
                    (commands[i].equals("--help") && commands[i + 1].equals("-a") && (i + 1) < commands.length)) {
                String key = commands[i];
                String value = i < commands.length ? commands[i + 1] : "";
                command.put(key, value);
            }
        }
        return command;
    }
}