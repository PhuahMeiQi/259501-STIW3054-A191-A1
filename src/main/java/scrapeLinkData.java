import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class scrapeLinkData {
    public List<Data> findAll(){
        try {
            System.out.println("");
            String URL = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
            Document doc = Jsoup.connect(URL).get();
            String tittle = doc.title();
            System.out.printf("%66s", tittle + "\n");
            System.out.println("-----------------------------------------------------------------------------------------");
            ArrayList<Data> result = new ArrayList<Data>();

            Elements linkdata = doc.select("table").select("tr");
            for (int i = 1;i<linkdata.size();i++ ) {
                Elements linkindata=linkdata.get(i).select("p");
                for (int j=0;j<linkindata.size();j++) {
                    String matchMatricResult = null;

                    Pattern matric = Pattern.compile("([0-9]{6})");
                    Matcher matchMatric = matric.matcher(linkindata.get(j).text());
                    if (matchMatric.find()){
                        System.out.print(i + " " + matchMatric.group());
                        matchMatricResult = matchMatric.group();
                    }else {
                        matchMatricResult= "";
                    }

                    Pattern name=Pattern.compile("(Name)(.*)(Matric)");
                    Matcher matchName = name.matcher(linkindata.get(j).text());

                    Pattern name2=Pattern.compile("(Name)(.*)(Link)");
                    Matcher matchName2 = name2.matcher(linkindata.get(j).text());

                    Pattern name3=Pattern.compile("(name)(.*)(link)");
                    Matcher matchName3 = name3.matcher(linkindata.get(j).text());

                    String matchNameResult = null;

                    if (matchName.find()){
                        System.out.print(i + " " + matchName.group(2));
                        matchNameResult = matchName.group(2).replaceFirst(" ","").replaceFirst(" : ","").replaceAll(": ","").replaceAll(":","");


                    }else if (matchName2.find()){
                        System.out.print(i + " " + matchName2.group(2));
                        matchNameResult = matchName2.group(2).replaceFirst(" ","").replaceFirst(" : ","").replaceAll(": ","").replaceAll(":","");

                    }else if (matchName3.find()){
                        System.out.print(i + " " + matchName3.group(2));
                        matchNameResult = matchName3.group(2).replaceFirst(" ","").replaceFirst(" : ","").replaceAll(": ","").replaceAll(":","");

                    }

                    Pattern link = Pattern.compile("https://.*");
                    Matcher matchLink = link.matcher(linkindata.get(j).text());
                    if(matchLink.find()){
                        System.out.println( ": " + matchLink.group());
                    }

                    result.add(new Data(matchMatricResult, matchNameResult,matchLink.group()));
                }
            }
            return result;

        }catch (Exception e){
            return null;
        }
    }
}