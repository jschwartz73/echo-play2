package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.echoGet());
    }

    public static Result echoGet() {

        String addr = "";
        String canHost = "";
        String host = "";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            addr = ip.getHostAddress();
            canHost = ip.getCanonicalHostName();
            host = ip.getHostName();
        } catch (UnknownHostException e) {
            addr = "Address not found: " + e.getMessage();
        }

        int port = Configuration.root().getInt("http.port", 9000);
        long time = System.currentTimeMillis();

        Logger.info("[GET] echo: " + addr + ":" + port + " / time: " + time);

        return ok(echoGet.render(addr, port, time, host, canHost));
    }

    public static Result echo() {
        Logger.info("");
        Logger.info("********************");
        Logger.info("* Message received *");
        Logger.info("***********************************");
        Logger.info("* ");
        Map<String, String[]> queryStrings = request().queryString();
        Logger.info("* Query String Params:");
        printMap(queryStrings);

        Logger.info("* ");
        Map<String, String[]> headers = request().headers();
        Logger.info("* Headers:");
        printMap(headers);

        Http.RequestBody body = request().body();
        Logger.info("* ");
//        Logger.info("* Body.text: " + body.asText());
//        Logger.info("* ");
//        Logger.info("* Body.json: " + body.asJson());
//        Logger.info("* ");
//        Logger.info("* Body.xml: " + body.asXml());
//        Logger.info("* ");
//        Logger.info("* Body.multi: " + body.asMultipartFormData());
//        Logger.info("* ");
        Http.RawBuffer buffer = body.asRaw();
        Logger.info("* Body.raw: " + new String(buffer.asBytes()));
        Logger.info("* Body.raw.size: " + buffer.size());
        Logger.info("* ");
        Logger.info("***********************************");

        return ok("OK");
    }

    private static void printMap(Map<String, String[]> map) {
        StringBuilder vals = new StringBuilder();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            Logger.info("* - " + entry.getKey() + ": " + flattenArray(entry.getValue()));
        }
    }

    private static String flattenArray(String[] array) {
        StringBuilder vals = new StringBuilder();
        for (String a: array) {
            vals.append(a)
                .append(", ");
        }
        return vals.toString();
    }
}
