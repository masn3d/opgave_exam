
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scrape implements Callable {

    Document doc = null;

    String url;

    public Scrape(String theUrl) {
        this.url = theUrl;
    }    

    @Override
    public Object call() throws Exception {

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            //return new Group("url not found", "url not found", "url not found");
            return null;//new Group("","","");
            //Logger.getLogger(ScraperTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        Elements authorsElem = doc.select("#authors");
        String authors = authorsElem.text();

        Elements classStrElem = doc.select("#class");
        String classID = classStrElem.text();

        Elements groupNoElem = doc.select("#group");
        String groupID = groupNoElem.text();

        return new Group(authors, classID, groupID);
    }

}
