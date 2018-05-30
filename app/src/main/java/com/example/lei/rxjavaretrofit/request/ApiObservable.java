package com.example.lei.rxjavaretrofit.request;

import com.example.lei.rxjavaretrofit.listener.CallBack;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiObservable<T> extends Observable<T> {


    /**
     * Creates an Observable with a Function to execute when it is subscribed to.
     * <p>
     * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead of this constructor,
     * unless you specifically have a need for inheritance.
     *
     * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is called
     */
    protected ApiObservable(OnSubscribe<T> f) {
        super(f);
    }

    public void excute(final CallBack<T> callBack) {
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBack != null){
                            callBack.onError();
                        }
                    }

                    @Override
                    public void onNext(T t) {
                        if(callBack != null){
                            callBack.onSuccess(t);
                        }
                    }
                });
    }
}
