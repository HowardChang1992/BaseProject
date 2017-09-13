package ${packageName};

import dagger.Component;

@Component(modules = ${presenterModuleClass}.class)
public interface ${componentClass} {

    void inject(${activityClass} activity);

}

