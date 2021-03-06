package com.bumptech.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public BytesResource(byte[] bytes2) {
        this.bytes = (byte[]) Preconditions.checkNotNull(bytes2);
    }

    @NonNull
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @NonNull
    public byte[] get() {
        return this.bytes;
    }

    public int getSize() {
        return this.bytes.length;
    }

    public void recycle() {
    }
}
