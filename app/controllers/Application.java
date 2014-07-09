package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.Map;

public class Application extends Controller {

    public static Result index() {
//        throw new RuntimeException("JEFF");
        return ok(index.render("Your new application is ready."));
    }

    public static Result echoGet() {
        Logger.info("echoGet: " + System.currentTimeMillis());
        return ok("Got it");
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
