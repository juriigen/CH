package ch.com.constructionhunt.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by jurgen on 10/10/2018.
 */


@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
