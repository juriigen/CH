package ch.com.constructionhunt.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by jurgen on 10/10/2018.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
