/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core.models.storage.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONTokener;


/**
 *
 * @author jecas
 */
public abstract class JsonStorage {
    public static JSONArray readJson(String ruta){
        File file = new File(ruta);
        try{
            try(Reader r = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)){
                return new JSONArray(new JSONTokener(r));
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        return null;
    }
    
    public static void writeJsonArray(String ruta, JSONArray array){
        File file = new File(ruta);
        try{
            file.getParentFile().mkdirs();
            try(Writer w = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
                w.write(array.toString(4));
            }
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
    }
}
