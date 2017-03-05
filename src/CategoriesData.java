import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kulsoom on 04/03/2017.
 */
public class CategoriesData {

    public static void main(String[] args) {

        File theCat = new File("categories.txt");
        File cleanCat = new File("cleanCategories.txt");

        try {
            Scanner console = new Scanner(theCat);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(cleanCat)));

            while (console.hasNextLine()) {

                String line = console.nextLine();

                if (line.equals("[]")) {

                    printWriter.print("[]");
                    printWriter.println();

                } else {

                    if (line.contains(".")) {

                        Pattern p3 = Pattern.compile("[\\[\\]]+");
                        Matcher m3 = p3.matcher(line);
                        line = m3.replaceAll("");

                        String[] splitter = line.split("\\.");

                        ArrayList<String> theList = new ArrayList<String>();

                        for (String string : splitter) {

                            String[] eachCat = string.split(":");

                            String hierarchyID = eachCat[1];


                            if (hierarchyID.equals("1")) {

                                if (theList.isEmpty()) {

                                    theList.add(eachCat[2]);
                                } else {

                                    printWriter.print(theList.toString());

                                    theList.clear();

                                    theList.add(eachCat[2]);
                                }
                            } else {
                                theList.add(eachCat[2]);
                            }
                        }

                        printWriter.print(theList.toString());
                        printWriter.println();

                    } else {

                        Pattern p3 = Pattern.compile("[\\[\\]]+");
                        Matcher m3 = p3.matcher(line);
                        line = m3.replaceAll("");

                        String[] eachCat = line.split(":");

                        printWriter.print("[" + eachCat[2] + "]");
                        printWriter.println();

                    }
                }
            }
            printWriter.close();
        } catch (IOException e) {
        }
    }
}
