import java.util.Scanner;
import java.util.regex.MatchResult;


public class ThreatAnalyzer {
	static String threatData =
		    "58.27.82.161@02/10/2005asdf\n" +
		    "204.45.234.40@02/11/2005wer\n" +
		    "58.27.82.161@02/11/2005asd\n" +
		    "58.27.82.161@02/12/2005asd\n" +
		    "58.27.82.161@02/12/2005iopuio\n";
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner s = new Scanner(threatData);
		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" + "(\\d{2}/\\d{2}/\\d{4})" + ".*";
		while(s.hasNext(pattern)){
			System.out.print(s.next(pattern));
			MatchResult match = s.match();
			String ip = match.group(1);
			String date = match.group(2);
			System.out.format("Threat on %s from %s\n", date, ip);
		}
	}

}
