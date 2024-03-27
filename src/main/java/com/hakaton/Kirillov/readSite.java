package com.hakaton.Kirillov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class readSite {
    public static List<String[]> read(){
        try {
            URL url = new URL("https://api.mexc.com/api/v3/ticker/price");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            connection.disconnect();
            String[] listOfPrise = responseContent.toString().split(",");
            List<String[]> splitStringList = new ArrayList<>();
            for(int i = 0; i < listOfPrise.length; i++){
                String[] buf = listOfPrise[i].split("\"");
                splitStringList.add(buf);
            }
            for(int i = 0; i < 10; i++){
                System.out.println(splitStringList.get(i)[3]);
            }
            return splitStringList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
