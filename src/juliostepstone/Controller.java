package juliostepstone;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Julio.
 */

public class Controller {

    String url; 
    public static List<String> list_links = new ArrayList<String>();
    public static List<String> list_host = new ArrayList<String>();
    public static List<String> list_host_repeat = new ArrayList<String>();

    //Constructor of the controller that receives a URL that we imputed via keyboard.
    public Controller(String url) {
        this.url = url;
        print("Fetching %s...", url);
    }

    //We upload the object-Element from the URL
    public Elements loadLink(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        print("\nLinks: (%d)", links.size());

        return links;
    }

    
    //Receives the links from the website to search, and:
    
  
    public void showLinks(Elements links) throws MalformedURLException {
        // 1. We save the links in a list
        for (Element link : links) {
            list_links.add(link.attr("abs:href"));
       
        }
        // 2. We create a URL for every link and get the host of each one, adding it to the list as long as it is not the host of the link that was originally entered.
        for (int i = 0; i < list_links.size(); i++) {

            String item = list_links.get(i);
            URL urlValue = new URL(item);
            String host = urlValue.getHost();
            //Check that the host is not equal to the host of the link that we enter by keyboard.
            if(!host.equals(getHostFromURL(url))){
                list_host.add(host); 
            }
      

        }
        //3. We go through the different hosts, check how many times they are repeated and once we have verified any duplicate hosts, we add them to the repeated list so as not to recount them.
        for (int i = 0; i < list_host.size(); i++) {
            int cont = 0;
            if (!list_host_repeat.contains(list_host.get(i))) {
                for (int j = 0; j < list_host.size(); j++) {
                    if (list_host.get(i).equals(list_host.get(j))) {
                        cont++;
                    }
                }
                list_host_repeat.add(list_host.get(i));

                System.out.println(list_host.get(i) + " - " + cont);
            }

        }
    }

    //Method that returns a host from a url ...     https://www.marca.com/noticias -->  www.marca.com
    private String getHostFromURL(String urlstr) throws MalformedURLException{
        
        URL urlnew = new URL(urlstr);
        return urlnew.getHost();
    }
    
    private void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
