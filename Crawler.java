import java.util.Iterator;
import java.util.List;

public abstract class Crawler {
    protected ArraySet<String> foundPages;
    protected ArraySet<String> skippedPages;

    /**
     * default constructor
     */
    public Crawler() {
        foundPages = new ArraySet<>();
        skippedPages = new ArraySet<>();
    }

    /**
     * Initiate a crawl on the given page
     * @param pageFileName - page file name
     */
    public abstract void crawl(String pageFileName);

    /**
     * returns foundpages as a list
     * @return List<String>
     */
    public List<String> foundPagesList() {
        return foundPages.asList();
    }

    /**
     * return skippedpages as a list
     * @return List<String>
     */
    public List<String> skippedPagesList() {
        return skippedPages.asList();
    }

    /**
     * returns foundpages as a string
     * @return String
     */
    public String foundPagesString() {
        Iterator iter = foundPages.iterator();
        String finalItem = "";
        while(iter.hasNext()) {
            String item = (String)iter.next();
            finalItem = finalItem + item + "\n";
        }
        return finalItem;
    }

    /**
     * returns skippedpages as a string
     * @return String
     */
    public String skippedPagesString() {
        Iterator iter = skippedPages.iterator();
        String finalItem = "";
        while(iter.hasNext()) {
            String item = (String)iter.next();
            finalItem = finalItem + item + "\n";
        }
        return finalItem;
    }

    /**
     * Returns true if the given pageFileName is valid and false otherwise
     * @param pageFileName - page file name
     * @return boolean
     */
    public static boolean validPageLink(String pageFileName) {
        if(pageFileName.startsWith("http://") || pageFileName.startsWith("https://") || pageFileName.startsWith("file://") || pageFileName.startsWith("javascript:")) {
            return false;
        }
        else if(pageFileName.endsWith(".html") || pageFileName.endsWith(".HTML")) {
            return true;
        }
        return false;
    }

}


