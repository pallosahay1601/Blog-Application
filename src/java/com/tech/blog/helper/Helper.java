/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author pallavi
 */
public class Helper {
    public static boolean deleteFile(String path)
    {
        boolean isDeleted=false;
        try{
            File file=new File(path);
            isDeleted=file.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isDeleted;
    }
    public static boolean saveFile(InputStream is, String path)
    {
        boolean isSaved=false;
        try{
            byte b[]=new byte[is.available()];
            is.read(b);
            FileOutputStream fos=new FileOutputStream(path);
            fos.write(b);
            fos.flush();
            fos.close();
            isSaved=true;
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isSaved;
    }
    
}
