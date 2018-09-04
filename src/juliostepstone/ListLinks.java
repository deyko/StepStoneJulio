package juliostepstone;


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * StepStone:
 * 
 * Program to list links from a URL.
 * 
 * @author Julio.
 */
public class ListLinks {
    
      public static Scanner scan= null;
      public static Controller controller= null;
      public static Elements links = null;
      
    public static void main(String[] args) throws IOException {
        //Declarations
        scan = new Scanner (System.in); 
        System.out.println("Escribe la web http: ");
        String url= scan.nextLine();
        controller = new Controller(url);  
        links = controller.loadLink(url);
        controller.showLinks(links);  
         
       
    }

    
}
