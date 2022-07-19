import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterativeCrawler extends Crawler{
    protected ArraySet<String> pendingPages;

    /**
     * default constructor
     */
    public IterativeCrawler() {
        pendingPages = new ArraySet<>();
    }

    /**
     * Implementation of crawling
     * @param pageFileName - page file name
     */
    @Override
    public void crawl(String pageFileName) {
        addPendingPage(pageFileName);
        crawlRemaining();
    }

    /**
     * crawls rest of pending pages
     */
    public void crawlRemaining() {
        while (pendingPages.size() > 0) {
            crawlNextPage();
        }
    }

    /**
     * adds file to pending pages
     * @param pageFileName
     */
    public void addPendingPage(String pageFileName) {
        pendingPages.add(pageFileName);
    }

    /**
     * returns pending pages size
     * @return int
     */
    public int pendingPagesSize() {
        return pendingPages.size();
    }

    /**
     * format string
     * @return String
     */
    public String pendingPagesString() {
        Iterator iter = pendingPages.iterator();
        String finalItem = "";
        while(iter.hasNext()) {
            String item = (String)iter.next();
            finalItem += finalItem + item + "\n";
        }
        return finalItem;
    }

    /**
     * crawls last item in pending pages
     */
    public void crawlNextPage() {
        List<String> pendingPagesList = pendingPages.asList();
        String pageName = pendingPagesList.get(pendingPages.size()-1);
        pendingPagesList.remove(pendingPages.size()-1);

        Document doc = null;
        try {
            doc = Jsoup.parse(new File(pageName), "UTF-8");

            ArrayList<Element> links = doc.select("a[href]");
            for (Element element : links) {
                String linkedPage = element.attr("href");

                if (!validPageLink(linkedPage)) {
                    skippedPages.add(linkedPage);
                    continue;
                }
                String linkFile = Util.relativeFileName(pageName, linkedPage);
                if (foundPages.contains(linkFile) || skippedPages.contains(linkFile)) {
                    continue;
                }
                else if (!new File(linkFile).exists()) {
                    skippedPages.add(linkFile);
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
