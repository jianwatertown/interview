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
public class HtmlParser {
    // Pattern pattern1 = Pattern.compile("(href\\s*=\\s*\")([^\"]*?)(\")", Pattern.CASE_INSENSITIVE);
    // Pattern pattern2 = Pattern.compile("(href\\s*=\\s*')([^']*?)(')", Pattern.CASE_INSENSITIVE);
    Pattern pattern = Pattern.compile("(href\\s*=\\s*[\"']?)([^\"'\\s>]*)([\"'>\\s])", Pattern.CASE_INSENSITIVE);
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
        // Write your code here
        List<String> results = new ArrayList<>();
        Matcher matcher = pattern.matcher(content);
        match(matcher, results);
        return results;
    }
    
    private void match(Matcher matcher, List<String> results) {
        while (matcher.find()) {
            String url = matcher.group(2);
            if (url.length() == 0 || url.startsWith("#")) continue;
            results.add(url);
        }
    }
}