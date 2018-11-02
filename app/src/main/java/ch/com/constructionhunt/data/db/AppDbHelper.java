package ch.com.constructionhunt.data.db;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import ch.com.constructionhunt.data.db.model.DaoMaster;
import ch.com.constructionhunt.data.db.model.DaoSession;
import ch.com.constructionhunt.data.db.model.Driver;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.data.db.model.LocationDao;
import io.reactivex.Observable;

/**
 * Created by jurgen on 11.10.2018.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertDriver(final Driver driver) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getDriverDao().insert(driver);
            }
        });
    }

    @Override
    public void clearDriverDb() {
        mDaoSession.getDriverDao().deleteAll();
    }
    @Override
    public void clearLocationDb() {
        mDaoSession.getLocationDao().deleteAll();
    }

    @Override
    public Observable<List<Driver>> getAllDrivers() {
        return Observable.fromCallable(new Callable<List<Driver>>() {
            @Override
            public List<Driver> call() throws Exception {
                return mDaoSession.getDriverDao().loadAll();
            }
        });
    }

    @Override
    public void saveLocation(final Location location) {
        mDaoSession.getLocationDao().insertInTx(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return mDaoSession.getLocationDao().loadAll();

    }

    @Override
    public Location getLocation(long id) {

        return mDaoSession.getLocationDao().queryBuilder().where(LocationDao.Properties.Id.eq(id)).unique();
    }
}
