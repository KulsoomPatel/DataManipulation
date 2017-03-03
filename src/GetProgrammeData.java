import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kulsoom on 03/03/2017.
 */
public class GetProgrammeData {
    public static void main(String[] args) {

        File mainFile = new File("complete_title.txt");
        File tvShowName = new File("tvShowName.txt");
        File series = new File("series.txt");
        File episode = new File("episode.txt");

        try {
            Scanner console = new Scanner(mainFile);

            PrintWriter pw1 = new PrintWriter(new BufferedWriter(new FileWriter(tvShowName)));
            PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(series)));
            PrintWriter pw3 = new PrintWriter(new BufferedWriter(new FileWriter(episode)));

            String seriesName = null;
            String episodeName = null;

            while (console.hasNextLine()) {
                String line = console.nextLine();

                String[] splitter = line.split(":");

                String showName = splitter[0];
                showName = removeChars(showName);

                if (splitter.length == 2) {
                    episodeName = splitter[1];
                    episodeName = removeChars(episodeName);

                } else if (splitter.length >= 3) {
                    seriesName = splitter[1];
                    episodeName = splitter[2];

                    seriesName = removeChars(seriesName);
                    episodeName = removeChars(episodeName);
                }


                pw1.write(showName);
                pw1.println();

                if (splitter.length == 1) {
                    pw2.print("N/A");
                    pw2.println();
                    pw3.print("N/A");
                    pw3.println();
                } else if (splitter.length == 2) {
                    pw2.print("N/A");
                    pw2.println();
                    pw3.print(episodeName);
                    pw3.println();
                } else {
                    pw2.print(seriesName);
                    pw2.println();
                    pw3.print(episodeName);
                    pw3.println();
                }

            }
            pw1.close();
            pw2.close();
            pw3.close();
        } catch (IOException e) {
        }
    }

    public static String removeChars(String name) {

        Pattern p = Pattern.compile("_");
        Matcher m = p.matcher(name);

        String cleanName = m.replaceAll(" ");

        cleanName = cleanName.trim();
        return cleanName;
    }
}
