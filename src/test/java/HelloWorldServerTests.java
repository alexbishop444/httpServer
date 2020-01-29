import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HelloWorldServerTests {
    @Before
    public void init() {
        HelloWorldServer helloWorldServer = new HelloWorldServer();
        helloWorldServer.startServer();
    }
    @Test
    public void testConnection() {
        try {
            URL url = new URL("http://localhost:8005/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);
            con.disconnect();
            Assert.assertEquals(200,status);
        }
        catch(Exception exception) {

        }
    }


}
