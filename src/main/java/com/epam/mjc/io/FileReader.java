package com.epam.mjc.io;

import javax.management.BadAttributeValueExpException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name="Def";    //  **  fields of Profile   **
        int age=0;    //
        String email="";   //
        long phone=0L;     //
        try (final var inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length !=2) throw new BadAttributeValueExpException("Bad format:"+line );
                switch (parts[0]) {
                    case "Name":name=parts[1];break;
                    case "Age": age=Integer.parseInt(parts[1]);break;
                    case "Email":email=parts[1];break;
                    case "Phone": phone=Long.parseLong(parts[1]);break;
                    default:
                        System.out.println("Invalid param "+parts[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Profile(name,age,email,phone);
    }
}
