package com.chang.template.activity.home;

import dagger.Component;

/**
 * Created by Howard Chang on 2017/4/18
 */
@Component(modules = MainPresenterModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

}
