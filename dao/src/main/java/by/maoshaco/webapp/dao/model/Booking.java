package by.maoshaco.webapp.dao.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date begin_date, end_date;
    private boolean state;

    @ManyToOne
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<Room>();

    public Booking() {
    }

    public Booking(long id, Date begin_date, Date end_date, boolean state, Profile profile) {
        this.id = id;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.profile = profile;
        this.state = state;
    }

    public Hotel getHotel() {
        return rooms.iterator().next().getHotel();
    }

    public String getRoomType() {
        return rooms.iterator().next().getType().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
