import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kulsoom on 03/03/2017.
 */
public class TagsData {

    public static void main(String[] args) {

        File theTags = new File("tags.txt");
        File cleanTags = new File("cleanTags.txt");

        try {
            Scanner console = new Scanner(theTags);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(cleanTags)));

            while (console.hasNextLine()) {

                String line = console.nextLine();

                Pattern p = Pattern.compile("\\.");
                Matcher m = p.matcher(line);
                line = m.replaceAll(",");

                Pattern p2 = Pattern.compile("_");
                Matcher m2 = p2.matcher(line);
                line = m2.replaceAll(" ");

                Pattern p3 = Pattern.compile("[\\[\\]]+");
                Matcher m3 = p3.matcher(line);
                line = m3.replaceAll("");

                String[] words = line.split(",");

                String[] newWords = new String[words.length];

                int i = 0;
                for (String word : words) {

                    newWords[i] = "\"" + word + "\"";
                    newWords[i] = newWords[i].trim();
                    i++;
                }
                printWriter.print(Arrays.toString(newWords));
                printWriter.println();


            }

            printWriter.close();

        } catch (IOException e) {

        }
    }
}
