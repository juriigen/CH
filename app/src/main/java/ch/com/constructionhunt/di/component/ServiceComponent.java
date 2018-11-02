package ch.com.constructionhunt.di.component;



import ch.com.constructionhunt.di.PerService;
import ch.com.constructionhunt.di.module.ServiceModule;
import ch.com.constructionhunt.service.SyncService;
import dagger.Component;

/**
 * Created by jurgen on 10/10/2018.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
