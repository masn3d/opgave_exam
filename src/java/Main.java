

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    private List<String> urls = new ArrayList<String>();
    private List<Group> groups = new ArrayList();
    private List<Future<Group>> futGroups = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();

        main.fillArray();

        try {
            main.startScrape();
        } catch (ExecutionException ex) {
            System.out.println(ex);
        }
    }

    public String startScrape() throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        for (String url : urls) {
            Scrape task = new Scrape(url);
            Future fut = es.submit(task);
            futGroups.add(fut);
        }
        System.out.println("Has Scraped information!");

        convertFutureListToGroupList(futGroups);
        es.shutdown();

        gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(groups);
    }

    public void convertFutureListToGroupList(List<Future<Group>> futureGroups) throws InterruptedException, ExecutionException {

        for (Future grp : futureGroups) {
            groups.add((Group) grp.get());
        }
    }

    public void fillArray() {

        //Class A
        urls.add("http://cphbusinessjb.cloudapp.net/CA2/");
        urls.add("http://ca2-ebski.rhcloud.com/CA2New/");
        urls.add("http://ca2-chrislind.rhcloud.com/CA2Final/");
        urls.add("http://ca2-pernille.rhcloud.com/NYCA2/");
        urls.add("https://ca2-jonasrafn.rhcloud.com:8443/company.jsp");
        urls.add("http://ca2javathehutt-smcphbusiness.rhcloud.com/ca2/index.jsp");

        //Class B
        urls.add("https://ca2-ssteinaa.rhcloud.com/CA2/");
        urls.add("http://tomcat-nharbo.rhcloud.com/CA2/");
        urls.add("https://ca2-cphol24.rhcloud.com/3.semCa.2/");
        urls.add("https://ca2-ksw.rhcloud.com/DeGuleSider/");
        urls.add("http://ca2-ab207.rhcloud.com/CA2/index.html");
        urls.add("http://ca2-sindt.rhcloud.com/CA2/index.jsp");
        urls.add("http://ca2gruppe8-tocvfan.rhcloud.com/");
        urls.add("https://ca-ichti.rhcloud.com/CA2/");

        //Class COS
        urls.add("https://ca2-9fitteen.rhcloud.com:8443/CA2/");
        urls.add("https://cagroup04-coolnerds.rhcloud.com/CA_v1/index.html");
        urls.add("http://catwo-2ndsemester.rhcloud.com/CA2/");
    }
}
