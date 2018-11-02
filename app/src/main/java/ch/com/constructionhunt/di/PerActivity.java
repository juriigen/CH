package ch.com.constructionhunt.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jurgen on 10/10/2018.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

