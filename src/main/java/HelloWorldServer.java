import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class HelloWorldServer {

    public void startServer() {
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0",8005), 0);
            server.createContext("/", new HelloWorldHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
