package ${packageName};

import android.content.Context;
import ${applicationPackage}.activity.BaseImplementModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class ${modelClass} extends BaseImplementModel implements ${contractClass}.Model {

    @Override
    public Observable<Object> performRequest(Context context) {

         return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {

            }
        }).delay(3, TimeUnit.SECONDS);

    }

}
