package entities;

import networking.DBTables;
import utils.BooleanMapper;
import utils.LocalDateTimeMapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Booking extends Entity<Booking>{
    private int bookingId;
    private int userId;
    private int eventId;
    private LocalDateTime bookingDate;
    private LocalDateTime cancelDate;


    @Override
    public String getUpdateQuery() {
        return String.format("UPDATE %s SET booking_date = '%s', cancel_date = '%s' WHERE id = '%s'", DBTables.BOOKING_TABLE, getBookingDate(), getCancelDate(), getBookingId());
    }

    @Override
    public String getInsertQuery() {
        return String.format("INSERT INTO %s VALUES (null, '%s', null, '%s', '%s')", DBTables.BOOKING_TABLE,
                getBookingDate(), getUserId(), getEventId());

    }

    @Override
    public void setObject(HashMap<String, String> object) {
        setBookingId(Integer.valueOf(object.get("id")));
        setBookingDate(LocalDateTimeMapper.map(object.get("booking_date")));
        setBookingDate(LocalDateTimeMapper.map(object.get("cancel_date")));
        setEventId(Integer.valueOf(object.get("event_id")));
        setUserId(Integer.valueOf(object.get("user_id")));
    }

    @Override
    public Booking getObject() {
        return this;
    }

    public int getEventId() {
        return eventId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }

}

