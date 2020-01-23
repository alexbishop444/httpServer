import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class HelloWorldHandler implements HttpHandler {
    private ArrayList<String> userList = new ArrayList<>();
    @Override
        public void handle(HttpExchange t) throws IOException {
            OutputStream os = t.getResponseBody();
            System.out.println(t.getRequestMethod());
            String request = t.getRequestURI().toString().replace("/","");
//          userList.add(t.getRequestURI().toString().replace("/",""));
            switch (t.getRequestMethod()) {
                case "GET":
                    getDateTimeResponse();
                    String response = getDateTimeResponse();
                    t.sendResponseHeaders(200, response.length());
                    os.write(response.getBytes());
                    os.close();
                    break;
                case "POST":
                    postRequest(request);
                    response = "success";
                    t.sendResponseHeaders(200, response.length());
                    os.write(response.getBytes());
                    os.close();
                    break;
                case "DELETE":
                    deleteRequest(request);
                    response = "success";
                    t.sendResponseHeaders(200, response.length());
                    os.write(response.getBytes());
                    os.close();
                    break;
            }

        }

    public void postRequest(String request) {
            String[] users = request.split(",");
            //validate user doesnt already exist
            userList.addAll(Arrays.asList(users));
            System.out.println(userList.get(0));
            System.out.println("Post Reached");
    }

    public void deleteRequest(String request) {
        userList.remove(request);
        System.out.println("Delete Reached");
    }



    public String getDateTimeResponse() {
            LocalTime localTime = LocalTime.now();
            Calendar cal = Calendar.getInstance();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            LocalDateTime localDateTime = LocalDateTime.now();
            int year  = localDateTime.getYear();
            int day   = localDateTime.getDayOfMonth();
            String month = new SimpleDateFormat("MMM").format(cal.getTime()).replace(".","");
            return "Hello Bob and " + userList.toString() +  " the time on the server is " + localTime.format(dateTimeFormatter) + " on " + day + " " + month + " " + year;
        }
}