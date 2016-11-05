package design.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <html>
  <body>
    <div>
      <a href="http://www.google.com" class="text-lg">Google</a>
      <a href="http://www.facebook.com" style="display:none">Facebook</a>
    </div>
    <div>
      <a href="https://www.linkedin.com">Linkedin</a>
      <a href = "http://github.io">LintCode</a>
    </div>
  </body>
</html>
 * @author jian.wang
 * [
  "http://www.google.com",
  "http://www.facebook.com",
  "https://www.linkedin.com",
  "http://github.io"
]
 */
public class HtmlHelper {

    /**
     * @param content source code
     * @return a list of links
     */
    public static List<String> parseUrls(String content) {
    	
        Pattern pattern = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        
        // Write your code here
        List<String> results = new ArrayList<>();
        Matcher matcher = pattern.matcher(content);
        match(matcher, results);
        return results;
    }
    
    private static void match(Matcher matcher, List<String> results) {
        while (matcher.find()) {
            String url = matcher.group();
            results.add(url);
        }
    }
}