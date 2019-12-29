import java.io.*;

public class FileReadingObject {

    // constructor
    public FileReadingObject() throws Exception { 

    } // end constructor

    // readingMethod
    public static String[] readingMethod(String fileName) throws Exception {
      String[] lines;

      // Read Weather Forecast
      BufferedReader reader = new BufferedReader(new FileReader
          (fileName));
      StringBuffer b = new StringBuffer();
      String line;

      // Variables for my array
      lines = new String[60];
      int c = 0;
      while (c < 60) {
        line = reader.readLine();
        b.append(line);
        lines[c] = line;
        c++;
      } // end while 
      reader.close();
      return lines;

    } // end readingMethod

    // main
    public static void main(String args[]) throws Exception {
        String nameOfFile;
        nameOfFile = "cut-weather.txt";
        new FileReadingObject();
        String[] lines = FileReadingObject.readingMethod(nameOfFile);
        for (String element: lines) {
            System.out.println(element);
        }

    } // end main

} // FileReadingObject
