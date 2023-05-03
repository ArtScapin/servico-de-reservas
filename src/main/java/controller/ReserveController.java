package controller;

import model.Seat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReserveController {
    public static ArrayList<Seat> initialState() {
        ArrayList<Seat> dataServer = new ArrayList<Seat>();
        dataServer.add(new model.Seat("A1"));
        dataServer.add(new model.Seat("A2"));
        dataServer.add(new model.Seat("A3"));
        dataServer.add(new model.Seat("B1"));
        dataServer.add(new model.Seat("B2"));
        dataServer.add(new model.Seat("B3"));
        dataServer.add(new model.Seat("C1"));
        dataServer.add(new model.Seat("C2"));
        dataServer.add(new model.Seat("C3"));
        return dataServer;
    }
    public static String getSlug(String[] request){
        try {
            String payload = request[0].split(" ")[1].split("\\?")[1];
            String slug = payload.split("&")[0].split("=")[1];
            return slug;
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
        return null;
    }
    public static String getName(String[] request){
        String payload = request[0].split(" ")[1].split("\\?")[1];
        String name = payload.split("&")[1].split("=")[1];
        name = name.replace("+", " ");
        return name;
    }
    public static boolean makeReserve(String[] request, ArrayList<Seat> dataServer){
        try {
            String slug = getSlug(request);
            String name = getName(request);

            for (Seat data: dataServer) {
                if (slug.compareTo(data.getSlug()) == 0) {
                    return data.setReservedBy(name);
                }
            }
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
        return false;
    }
}
