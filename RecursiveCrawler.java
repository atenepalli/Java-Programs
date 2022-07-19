import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;

public class RecursiveCrawler extends Crawler{

    /**
     * default constructor
     */
    public RecursiveCrawler() {
    }

    /**
     * Implementation of crawling with recursion
     * @param pageFileName - page file name
     */
    @Override
    public void crawl(String pageFileName) {
        foundPages.add(pageFileName);
        try {
            Document doc = Jsoup.parse(new File(pageFileName), "UTF-8");
            ArrayList<Element> links = doc.select("a[href]");
            for(Element element : links) {
                String linkedPage = element.attr("href");

                if (!validPageLink(linkedPage)) {
                    skippedPages.add(linkedPage);
                    continue;
                }
                String linkFile = Util.relativeFileName(pageFileName, linkedPage);
                if(foundPages.contains(linkFile) || skippedPages.contains(linkFile)) {
                    continue;
                }
                else if(!new File(linkFile).exists()) {
                    skippedPages.add(linkFile);
                    continue;
                }

                if(validPageLink(linkFile)) {
                    crawl(linkFile);
                }
            }
        }

        catch (Exception e) {
            System.out.println("Crawler failed");
        }
    }
}
