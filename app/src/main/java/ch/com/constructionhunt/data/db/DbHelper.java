package ch.com.constructionhunt.data.db;

import java.util.List;

import ch.com.constructionhunt.data.db.model.Driver;
import ch.com.constructionhunt.data.db.model.Location;
import io.reactivex.Observable;

/**
 * Created by jurgen on 11.10.2018.
 */

public interface DbHelper {

    Observable<Long> insertDriver(final Driver driver);

    void clearDriverDb();
     void clearLocationDb();
    Location getLocation(long id);
    Observable<List<Driver>> getAllDrivers();

    List<Location> getAllLocations();

     void saveLocation(Location Location);

}
