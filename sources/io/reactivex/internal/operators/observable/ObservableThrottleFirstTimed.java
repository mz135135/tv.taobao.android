package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableThrottleFirstTimed<T> extends AbstractObservableWithUpstream<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public ObservableThrottleFirstTimed(ObservableSource<T> source, long timeout2, TimeUnit unit2, Scheduler scheduler2) {
        super(source);
        this.timeout = timeout2;
        this.unit = unit2;
        this.scheduler = scheduler2;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new DebounceTimedObserver(new SerializedObserver(t), this.timeout, this.unit, this.scheduler.createWorker()));
    }

    static final class DebounceTimedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 786994795061867455L;
        final Observer<? super T> actual;
        boolean done;
        volatile boolean gate;
        Disposable s;
        final long timeout;
        final TimeUnit unit;
        final Scheduler.Worker worker;

        DebounceTimedObserver(Observer<? super T> actual2, long timeout2, TimeUnit unit2, Scheduler.Worker worker2) {
            this.actual = actual2;
            this.timeout = timeout2;
            this.unit = unit2;
            this.worker = worker2;
        }

        public void onSubscribe(Disposable s2) {
            if (DisposableHelper.validate(this.s, s2)) {
                this.s = s2;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.gate && !this.done) {
                this.gate = true;
                this.actual.onNext(t);
                Disposable d = (Disposable) get();
                if (d != null) {
                    d.dispose();
                }
                DisposableHelper.replace(this, this.worker.schedule(this, this.timeout, this.unit));
            }
        }

        public void run() {
            this.gate = false;
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }
}
