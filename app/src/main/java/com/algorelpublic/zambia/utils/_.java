package com.algorelpublic.zambia.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.androidquery.AQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class _ {

    private static boolean DEBUG = true;
    private static final String fileName = "._log";
    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
        if (_.sAQuery == null) {
            _.sAQuery = new AQuery(context);
        }
    }

    private static AQuery sAQuery = null;

    /**
     * @param msg
     */
    public static void log(Object msg) {
        if (DEBUG) {
            Log.e("Doc To Me: >", "" + msg.toString());
            /*if(msg.toString().contains(Constants.LOG_TAGS.WEEMO_LOG_TAG)) {
                writeLogTextFile(msg.toString()+ System.getProperty("line.separator"));
            }*/
        }

    }

    /**
     * @param strWrite
     */

    private static void writeLogTextFile(String strWrite) {

        try {
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File myFile = new File(fullPath + "/" + fileName);
            FileWriter writer = new FileWriter(myFile, true);
            writer.write(strWrite);
            writer.flush();
            writer.close();
        } catch (Exception e) {
        }
    }

    public static String readLogFile(Context context) {
        String logBreaker = "\n>>>>>>>>>>><<<<<<<<<<\n";
        String stringToReturn = "";
        try {
           /* if(isSdReadable())    // isSdReadable()e method is define at bottom of the post
            {*/
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;
            File mFile = new File(fullPath);
            InputStream inputStream = new FileInputStream(mFile);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString + logBreaker);
                }
                inputStream.close();
                stringToReturn = stringBuilder.toString();
            }
            /*}*/
        } catch (FileNotFoundException e) {
            Log.e("readLogFile ", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("readLogFile ", "Can not read file: " + e.toString());
        }

        return stringToReturn;
    }
}