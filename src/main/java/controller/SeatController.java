package controller;

import model.Seat;
import service.Log;

import java.util.ArrayList;

public class SeatController {
    public static ArrayList<Seat> initialState() {
        ArrayList<Seat> dataServer = new ArrayList<>();
        dataServer.add(new Seat("A1"));
        dataServer.add(new Seat("A2"));
        dataServer.add(new Seat("A3"));
        dataServer.add(new Seat("B1"));
        dataServer.add(new Seat("B2"));
        dataServer.add(new Seat("B3"));
        dataServer.add(new Seat("C1"));
        dataServer.add(new Seat("C2"));
        dataServer.add(new Seat("C3"));
        return dataServer;
    }

    public static boolean reserveSeat(String[] request, String ip, ArrayList<Seat> dataServer){
        try {
            String slug = getSlug(request);
            String name = getName(request);

            for (Seat seat: dataServer) {
                if (slug.compareTo(seat.getSlug()) == 0) {

                    if(seat.setReservedBy(name, ip)) {
                        Log.write("The seat " + slug + " has been reserved!");
                        return true;
                    }
                    Log.write("Failed to reserve the seat " + slug + "!");
                    return false;
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }

    public static String getSlug(String[] request){
        try {
            String payload = request[0].split(" ")[1].split("\\?")[1];
            return payload.split("&")[0].split("=")[1];
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public static String getName(String[] request){
        String payload = request[0].split(" ")[1].split("\\?")[1];
        String name = payload.split("&")[1].split("=")[1];
        name = name.replace("+", " ");
        return name;
    }
}
