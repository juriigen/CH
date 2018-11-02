package ch.com.constructionhunt.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


/**
 * Created by jurgen on 11.10.2018.
 */

@Entity(nameInDb = "location")
public class Location {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "driver_id")
    private Long driverId;

    @Property(nameInDb = "longitude")
    private double longitude;

    @Property(nameInDb = "latitude")
    private double latitude;

    @Property(nameInDb = "state")
    private String state;

    @Property(nameInDb = "created_at")
    private String createdAt;

    @Property(nameInDb = "updated_at")
    private String updatedAt;

    @Generated(hash = 39820398)
    public Location(Long id, Long driverId, double longitude, double latitude, String state, String createdAt, String updatedAt) {
        this.id = id;
        this.driverId = driverId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

