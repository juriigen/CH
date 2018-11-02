package ch.com.constructionhunt.di.component;

import android.app.Application;
import android.content.Context;


import javax.inject.Singleton;

import ch.com.constructionhunt.MvpApp;
import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.di.ApplicationContext;
import ch.com.constructionhunt.di.module.ApplicationModule;
import ch.com.constructionhunt.service.SyncService;
import dagger.Component;

/**
 * Created by jurgen on 10/10/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}