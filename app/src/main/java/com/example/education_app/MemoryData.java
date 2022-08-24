package com.example.education_app;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class MemoryData {

    public static void saveData(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("dataa.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savelastmessage(String currentTimeStamp, String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("lastmessage.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveMessage(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("message.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveName(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("name.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static String getData(Context context){
        String data = "";
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("dataa.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getMessage(Context context, String chatId){
        String data = "0";
        try{
            FileInputStream fis = context.openFileInput(chatId+".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getName(Context context){
        String data = "0";
        try{
            FileInputStream fis = context.openFileInput("name.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static String getLastMessage(Context context, String chatId){
        String data = "0";
        try{
            FileInputStream fis = context.openFileInput(chatId+".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
