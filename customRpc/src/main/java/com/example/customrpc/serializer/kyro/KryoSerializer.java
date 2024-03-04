package com.example.customrpc.serializer.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.example.customrpc.rpcTransmitObject.RpcRequest;
import com.example.customrpc.rpcTransmitObject.RpcResponse;
import com.example.customrpc.serializer.Serializer;

/**
 * kryo主要的类有
 */
public class KryoSerializer implements Serializer {
    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        return kryo;
    });

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> tClass) {
        return null;
    }
}
