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

public class httpurlconnection {
    public String httpurlconnection(String s) throws IOException {
        URL url = new URL("http://47.115.149.151:8888/api/search?value="+s+"&type=1");
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        URLConnection urlConnection = url.openConnection();
        urlConnection.setAllowUserInteraction(false);
        isr = new InputStreamReader(url.openStream());
        br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        String pt= "(\\d+)";
        Pattern r = Pattern.compile(pt);
        Matcher m = r.matcher(sb.toString());
        String simid;
        if (m.find( )) {
            simid = m.group(0);
        } else {
            return "NO MATCH";
        }
        return simid;

    }


}
