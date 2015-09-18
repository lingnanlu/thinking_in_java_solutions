import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;

import static io.lingnanlu.github.Print.*;

public class p13_12 {
  static public final String POEM =
    "Twas brillig, and the slithy toves\n" +
    "Did gyre and gimble in the wabe.\n" +
    "All mimsy were the borogoves,\n" +
    "And the mome raths outgrabe.\n\n" +
    "Beware the Jabberwock, my son,\n" +
    "The jaws that bite, the claws that catch.\n" +
    "Beware the Jubjub bird, and shun\n" +
    "The frumious Bandersnatch.";
  public static void main(String[] args) {
    Matcher m =
      Pattern.compile("\\b[a-z]+")
        .matcher(POEM);
    Set<String> wordSet = new HashSet<String>();
    while(m.find()) {
        printnb(m.group() + " ");
        wordSet.add(m.group());
    }
    
    
    print();
    print(wordSet.size());
  }
}