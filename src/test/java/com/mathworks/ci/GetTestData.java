package com.mathworks.ci;

import java.io.InputStream;
import java.util.Properties;

public class GetTestData {
    static String value="";
    static InputStream inputStream;

    public static String getPropValues(String key){
        try{
            Properties prop=new Properties();

            inputStream= GetTestData.class.getClassLoader().getResourceAsStream("testdata.properties");
            if(inputStream!=null){
                prop.load(inputStream);
            } else {
                System.out.println("NOT ABLE TO FIND FILE");
            }
            value=prop.getProperty(key);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return value;
    }

    public static String[] getList(String key){
        String[] res = null;
        try{
            Properties prop=new Properties();

            inputStream= GetTestData.class.getClassLoader().getResourceAsStream("testdata.properties");
            if(inputStream!=null){
                prop.load(inputStream);
            } else {
                System.out.println("NOT ABLE TO FIND FILE");
            }
            res=prop.getProperty(key).split(",");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return res;

    }
}
