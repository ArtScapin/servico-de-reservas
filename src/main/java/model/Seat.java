package model;

import service.Server;

public class Seat {
    private String slug;
    private boolean reserved;
    private String reservedBy;
    private String ip;
    private String dateTime;

    public Seat(String slug) {
        this.slug = slug;
        this.reserved = false;
    }

    public boolean setReservedBy(String name, String ip) {
        if (this.reserved == false) {
            this.reserved = true;
            this.reservedBy = name;
            this.ip = ip;
            this.dateTime = Server.getDateTime();
            return true;
        }
        return false;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getSlug() {
        return slug;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }
}
