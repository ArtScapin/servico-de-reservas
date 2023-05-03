package model;

import controller.ReserveController;
import controller.Server;

import java.time.LocalDateTime;

public class Seat {
    private String slug;
    private boolean reserved;
    private String reservedBy;
    private String dateTime;

    public Seat(String slug) {
        this.slug = slug;
        this.reserved = false;
        this.reservedBy = "";
    }

    public Seat(String slug, String reservedBy) {
        this.slug = slug;
        this.reserved = true;
        this.reservedBy = reservedBy;
    }

    public boolean setReservedBy(String name) {
        if (this.reserved == false) {
            this.reserved = true;
            this.reservedBy = name;
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
