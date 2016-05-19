package by.maoshaco.webapp.dao.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String name;    
    private String address;
    private int rating;

    @ManyToOne
    private Profile manager;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="hotel", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Long, Room> rooms = new HashMap<Long, Room>();

    public Hotel() {}
    
    public Hotel(String name, String address, int rating) {
    	this.name = name;
    	this.address = address;
    	this.rating = rating;
    }
    
    public String getAddress() {
        return address;
    }
    
    public long getId() {
        return id;
    }

    public Profile getManager() {
		return manager;
	}

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }
    
    public Map<Long, Room> getRooms() {
		return rooms;
	}

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setManager(Profile manager) {
		this.manager = manager;
	}

	public void setName(String name) {
        this.name = name;
    }
	
	public void setRating(int rating) {
        this.rating = rating;
    }

	public void setRooms(Map<Long, Room> rooms) {
		this.rooms = rooms;
	}

	@Override
    public String toString() {
    	return "Id: " + getId() + "\nName: " + getName() + "\nAddress: " + getAddress() + "\nRating: " + getRating() + "\nManager: " + getManager();
    }
}

