package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;

public final class SingleZipIterable<T, R> extends Single<R> {
    final Iterable<? extends SingleSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    public SingleZipIterable(Iterable<? extends SingleSource<? extends T>> sources2, Function<? super Object[], ? extends R> zipper2) {
        this.sources = sources2;
        this.zipper = zipper2;
    }

    /* JADX WARNING: type inference failed for: r8v14, types: [java.lang.Object[]] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribeActual(io.reactivex.SingleObserver<? super R> r12) {
        /*
            r11 = this;
            r8 = 8
            io.reactivex.SingleSource[] r1 = new io.reactivex.SingleSource[r8]
            r4 = 0
            java.lang.Iterable<? extends io.reactivex.SingleSource<? extends T>> r8 = r11.sources     // Catch:{ Throwable -> 0x003b }
            java.util.Iterator r9 = r8.iterator()     // Catch:{ Throwable -> 0x003b }
            r5 = r4
        L_0x000c:
            boolean r8 = r9.hasNext()     // Catch:{ Throwable -> 0x0087 }
            if (r8 == 0) goto L_0x0043
            java.lang.Object r7 = r9.next()     // Catch:{ Throwable -> 0x0087 }
            io.reactivex.SingleSource r7 = (io.reactivex.SingleSource) r7     // Catch:{ Throwable -> 0x0087 }
            if (r7 != 0) goto L_0x0027
            java.lang.NullPointerException r8 = new java.lang.NullPointerException     // Catch:{ Throwable -> 0x0087 }
            java.lang.String r9 = "One of the sources is null"
            r8.<init>(r9)     // Catch:{ Throwable -> 0x0087 }
            io.reactivex.internal.disposables.EmptyDisposable.error((java.lang.Throwable) r8, (io.reactivex.SingleObserver<?>) r12)     // Catch:{ Throwable -> 0x0087 }
            r4 = r5
        L_0x0026:
            return
        L_0x0027:
            int r8 = r1.length     // Catch:{ Throwable -> 0x0087 }
            if (r5 != r8) goto L_0x0035
            int r8 = r5 >> 2
            int r8 = r8 + r5
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r1, r8)     // Catch:{ Throwable -> 0x0087 }
            r0 = r8
            io.reactivex.SingleSource[] r0 = (io.reactivex.SingleSource[]) r0     // Catch:{ Throwable -> 0x0087 }
            r1 = r0
        L_0x0035:
            int r4 = r5 + 1
            r1[r5] = r7     // Catch:{ Throwable -> 0x003b }
            r5 = r4
            goto L_0x000c
        L_0x003b:
            r2 = move-exception
        L_0x003c:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r2)
            io.reactivex.internal.disposables.EmptyDisposable.error((java.lang.Throwable) r2, (io.reactivex.SingleObserver<?>) r12)
            goto L_0x0026
        L_0x0043:
            if (r5 != 0) goto L_0x004f
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException
            r8.<init>()
            io.reactivex.internal.disposables.EmptyDisposable.error((java.lang.Throwable) r8, (io.reactivex.SingleObserver<?>) r12)
            r4 = r5
            goto L_0x0026
        L_0x004f:
            r8 = 1
            if (r5 != r8) goto L_0x0064
            r8 = 0
            r8 = r1[r8]
            io.reactivex.internal.operators.single.SingleMap$MapSingleObserver r9 = new io.reactivex.internal.operators.single.SingleMap$MapSingleObserver
            io.reactivex.internal.operators.single.SingleZipIterable$SingletonArrayFunc r10 = new io.reactivex.internal.operators.single.SingleZipIterable$SingletonArrayFunc
            r10.<init>()
            r9.<init>(r12, r10)
            r8.subscribe(r9)
            r4 = r5
            goto L_0x0026
        L_0x0064:
            io.reactivex.internal.operators.single.SingleZipArray$ZipCoordinator r6 = new io.reactivex.internal.operators.single.SingleZipArray$ZipCoordinator
            io.reactivex.functions.Function<? super java.lang.Object[], ? extends R> r8 = r11.zipper
            r6.<init>(r12, r5, r8)
            r12.onSubscribe(r6)
            r3 = 0
        L_0x006f:
            if (r3 >= r5) goto L_0x0085
            boolean r8 = r6.isDisposed()
            if (r8 == 0) goto L_0x0079
            r4 = r5
            goto L_0x0026
        L_0x0079:
            r8 = r1[r3]
            io.reactivex.internal.operators.single.SingleZipArray$ZipSingleObserver<T>[] r9 = r6.observers
            r9 = r9[r3]
            r8.subscribe(r9)
            int r3 = r3 + 1
            goto L_0x006f
        L_0x0085:
            r4 = r5
            goto L_0x0026
        L_0x0087:
            r2 = move-exception
            r4 = r5
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.single.SingleZipIterable.subscribeActual(io.reactivex.SingleObserver):void");
    }

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(SingleZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
