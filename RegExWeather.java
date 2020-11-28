import java.io.*;
import java.util.regex.*;

//-----------------------------------------------------------
public class RegExWeather {

	public RegExWeather() throws Exception {
		// Constructor
		// Create patterns to match ST. ALBANS & $$
		// Pattern p = Pattern.compile("ST. ALBANS");
		Pattern p = Pattern.compile("St. Albans");
		// Pattern p = Pattern.compile("Burlington,");
		Pattern q = Pattern.compile("\\$\\$");

		PrintWriter pw = new PrintWriter(new FileWriter("cut-weather.txt"));
		BufferedReader br = new BufferedReader(new FileReader("retrieved-weather.txt"));
		Matcher m;
		Matcher n;
		String lineholder = "dummyline";
		String[] textarray1 = new String[8000];
		int i = 0;
		int j = 0;
		int firstinstance = 0;

		// Fill an array with the text from retrieved weather
		try {
			while (((lineholder = br.readLine()) != null)) {
				textarray1[i] = lineholder;
				i++;
			} // end while
		} finally {
			br.close();
		}

		m = p.matcher(textarray1[0]);

		// For-loop to find ST. ALBANS
		for (i = 0; firstinstance == 0; i++) {
			m = p.matcher(textarray1[i]);
			if (m.find()) {
				System.out.println("Found on line " + i + ":");
				System.out.println("\"" + textarray1[i] + "\"");
				System.out.println();
				// j variable is needed to back up a few lines, because
				// ST. ALBANS is contained a few lines into a multi-line
				// paragraph
				j = (i - 3);

				/*
				 * firstinstance variable needed because multiple ST. ALBANS entries are often
				 * in the retrieved weather texts. The topmost one is the most recent
				 */
				firstinstance = 1;
			}
		} // end for-loop for ST. ALBANS

		// While loop to find end of entry with double dollar-signs
		n = q.matcher(textarray1[j]);
		while (!(n.find())) {
			n = q.matcher(textarray1[j]);
			pw.println(textarray1[j]);
			System.out.println(textarray1[j]);
			j++;
		}

		// Have to close buffers (at least pw, I think) for
		// file to get *written*
		// br.close();
		pw.close();

	} // end constructor

	public static void main(String[] args) throws Exception {
		new RegExWeather();
	} // main
//-----------------------------------------------------------
} // RegExWeather class
