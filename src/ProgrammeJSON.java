import org.json.simple.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Kulsoom on 18/03/2017.
 */
public class ProgrammeJSON {
    public static void main(String[] args) {

        File cleanCSV = new File("clean_twoweek_proglist.csv");
        File tagsData = new File("cleanTags.txt");
        File cleanCategories = new File("cleanCategories.txt");

        File cleanJSON = new File("clean_programme.json");

        try {
            Scanner console = new Scanner(cleanCSV);
            Scanner console2 = new Scanner(tagsData);
            Scanner console3 = new Scanner(cleanCategories);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(cleanJSON)));

            while (console.hasNextLine()) {

                String line = console.nextLine();
                String tags = console2.nextLine();
                String categories = console3.nextLine();

                String[] splitter = line.split(",");

                JSONObject theObject = new JSONObject();
                JSONObject programmeTitle = new JSONObject();

                theObject.put("_id", splitter[0]);
                theObject.put("start_time", new Integer(splitter[1]));
                theObject.put("end_time", new Integer(splitter[2]));

                //Creating the complete_title object
                programmeTitle.put("name", splitter[3]);

                if (!splitter[4].equals("")) {
                    programmeTitle.put("series", splitter[4]);
                }

                if (!splitter[5].equals("")) {

                    String theDate = splitter[5];
                    programmeTitle.put("episode", theDate);
                }

                theObject.put("complete_title", programmeTitle);
                theObject.put("media_type", splitter[6]);

                if (!splitter[7].equals("")) {
                    theObject.put("service", splitter[7]);
                }

                theObject.put("is_clip", new Integer(splitter[8]));


                if (!tags.equals("[\"\"]")) {
                    theObject.put("tags", tags);
                }

                if (!categories.equals("[\"\"]")) {

                    JSONObject categoriesObject = new JSONObject();
                    String[] theCategories = categories.split("]");

                    int catLen = theCategories.length;

                    String category = "category";

                    for (int i = 1; i <= catLen; i++) {
                        if (!theCategories[i - 1].equals("")) {

                            categoriesObject.put(category + i, theCategories[i - 1] + "]");
                        }

                    }

                    theObject.put("categories", categoriesObject);
                }

                String objectAsString = theObject.toJSONString().replace("\\/", "/");
                objectAsString = objectAsString.replace("\\", "");
                objectAsString = objectAsString.replace("\"[", "[");
                objectAsString = objectAsString.replace("]\"", "]");
                printWriter.write(objectAsString);
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {

        }

    }
}
