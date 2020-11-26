package com.example.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class compare {
    public String find(String s) throws IOException{
        String simid;
        URL url_2 = new URL("http://47.115.149.151:8888/api/song/simi?id="+s+"&limit=1");
        StringBuffer sb_2 = new StringBuffer();
        InputStreamReader isr_2 = null;
        BufferedReader br_2 = null;
        HttpURLConnection conn_2 = (HttpURLConnection) url_2.openConnection();
        URLConnection urlConnection_2 = url_2.openConnection();
        urlConnection_2.setAllowUserInteraction(false);
        isr_2 = new InputStreamReader(url_2.openStream());
        br_2 = new BufferedReader(isr_2);
        String line_2;
        while ((line_2 = br_2.readLine()) != null)
        {
            sb_2.append(line_2);
        }
        String pattern = "(?<=\"id\"\\:)(\\d+)(?=,\"recommendReason\")";
        Pattern r_2 = Pattern.compile(pattern);
        Matcher m = r_2.matcher(sb_2.toString());
        if (m.find( )) {
            simid = m.group(0);
        } else {
            return "NO MATCH";
        }
        return simid;
    }
}
