package lgs.bbs.comm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxCommander {
    public static String exec(String command){
        String result = "";
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        StringBuffer sb = new StringBuffer();
        try{
            p=rt.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String cl = null;
            while((cl=in.readLine())!=null){
                sb.append(cl);
            }
            result = sb.toString();
            in.close();
        }catch(IOException e){
            e.printStackTrace();
            return "";
        }
        return result;
    }
}