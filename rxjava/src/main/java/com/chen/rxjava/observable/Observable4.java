package com.chen.rxjava.observable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public class Observable4 {

    public static void main(String[] args) {
        Consumer<Long> subscriber1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };
        Consumer<Long> subscriber2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("    subscriber2: " + aLong);
            }
        };
        Consumer<Long> subscriber3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("        subscriber3: " + aLong);
            }
        };
        ConnectableObservable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(emitter::onNext);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .publish();
        observable.connect();
        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);
        try {
            Thread.sleep(120L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observable.subscribe(subscriber3);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
