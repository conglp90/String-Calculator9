import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Calculator {
	public static int add(String input) {
		int kq = 0;   
		if(input==null||input.isEmpty())
			return 0;
		input=toStandard(input);
		ArrayList<Integer> numbers=toArrayNumber(input);
		checkNegatives(numbers);
		kq=sum(numbers);
		return kq;
	}
	private static void checkNegatives(ArrayList<Integer> numbers) {
		String negatives="negatives not allowed:";
		boolean isNegatives=false;
		for (int integer : numbers) {
			if (integer<0) {
				negatives+= isNegatives ? ", "+integer : " "+integer;
				isNegatives=true;
			}
		}
		if(isNegatives)
			throw new RuntimeException(negatives);
	}
	private static String toStandard(String input) {
		ArrayList<String> delimiters=toArrayDelimiter(input);
		if(input.contains("//"))
			input=input.substring(input.indexOf("\n"));
		for (String string : delimiters) {
			input=input.replaceAll(Pattern.quote(string),",");
		}
		return input;
	}
	private static ArrayList<String> toArrayDelimiter(String input) {
		if(input.contains("//"))
			input=input.substring(0,input.indexOf("\n"));
		Matcher matcher=Pattern.compile("\\[([^\\[]+)\\]").matcher(input);
		ArrayList<String> delimiters=new ArrayList<String>();
		while (matcher.find()) {
			delimiters.add(matcher.group(1));
			System.out.println(matcher.group(1));
		}
		return delimiters;
	}
	private static ArrayList<Integer> toArrayNumber(String input) {
		ArrayList<Integer> numbers=new ArrayList<Integer>();
		Matcher matcher=Pattern.compile("-?[0-9]+").matcher(input);
		while (matcher.find()) {
			int temp=Integer.parseInt(matcher.group(0));
			if (temp < 1001)
				numbers.add(temp);
			else numbers.add(0) ;
		}
		return numbers;
	}
	private static int sum(ArrayList<Integer> numbers2) {
		int kq=0;
		for (int i: numbers2) {
			kq+=i;
		}
		return kq;
	}
}
