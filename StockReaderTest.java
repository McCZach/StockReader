package Project;

/*
 * @author Zach
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class StockReaderTest {
	public static void main(String [] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		String input = "";
		System.out.println("Please enter the name of the stock you would like to search (AAPL; TSLA): ");
		input = scan.nextLine();
		
		URL url = new URL("https://finance.yahoo.com/quote/".concat(input));
		
		URLConnection urlCon = url.openConnection();
		InputStreamReader inStr = new InputStreamReader(urlCon.getInputStream());
		BufferedReader buff = new BufferedReader(inStr);
		String price = "ERROR! COULD NOT FIND";
		String line = buff.readLine();
		
		
		while(line != null) {
			if(line.contains("\"currentPrice\":{\"raw\"")) {
				int target = line.indexOf("\"currentPrice\":{\"raw\"");
				int deci = line.indexOf(".", target);
				int start = deci;
				while(line.charAt(start) != '\"') {
					start--;
				}
				price = line.substring(start + 2, deci + 3);
			}
			line = buff.readLine();
		}
		System.out.println(price);
	}
}
