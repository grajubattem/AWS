package com.raju.lamba.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Base64;

public class Base64DecodePdf {
  public static void main(String[] args) {
    File inputFile = new File("C:/Users/Raju/Downloads/input.pdf");
    File outputFile = new File("C:/Users/Raju/Downloads/output11.pdf");
    
    try  {
    	 FileOutputStream fos = new FileOutputStream(outputFile); 
      // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      StringBuilder stringBuilder = new StringBuilder();
      String line = null;
      String ls = System.getProperty("line.separator");
      while ((line = reader.readLine()) != null) {
      	stringBuilder.append(line);
      	stringBuilder.append(ls);
      }
      // delete the last new line separator
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      reader.close();

      String content = stringBuilder.toString();

      byte[] decoder = Base64.getDecoder().decode(content);

      fos.write(decoder);
      System.out.println("PDF File Saved");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
