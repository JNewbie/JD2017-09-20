package by.it.shelkovich.jd2_06;

import java.io.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public enum Logger {
    INSTANCE;

    private File logFile = new File(System.getProperty("user.dir") + "/src/by/it/shelkovich/jd2_06/log.txt");
    private List<String> logMsgs = new ArrayList<>();

    public void log(String msg) {
        if (logFile.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(logFile))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    logMsgs.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DateFormat df = DateFormat.getDateInstance();
        DateFormat tf = DateFormat.getTimeInstance();
        logMsgs.add(df.format(new Date())+" "+tf.format(new Date())+": "+msg);
        while(logMsgs.size()>50) logMsgs.remove(0);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile))) {
            for (String logMsg : logMsgs) {
                bufferedWriter.write(logMsg);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}