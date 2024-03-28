package com.example.basejava.netty;

import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

public class SelectorExample {
    public static void main(String[] args) throws IOException {
        AbstractSelector selector = SelectorProvider.provider().openSelector();
      //  IOUtil.makePipe();

    }
}
