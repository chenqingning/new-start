package com.anonymity;

public class MapFuse {

    private String a;
    public String c = "22";

    public void testInvokeAnonymity() {
        MapFuseS2 mapFuseS2 = new MapFuseS2();
        mapFuseS2.getB2();
    }

    static final class MapFuseS {
        private String b;
    }

    static class MapFuseS1 {
        private String b = "11";

        public void getB2() {
            String b = this.b;
        }
    }

    class MapFuseS2 {
        private String b = "22";
        MapFuseS1 mapFuseS1 = new MapFuseS1();

        public void getB2() {
            String b = this.b;
            testInvokeAnonymity();
            String c1 = c;
        }
    }

    public static void main(String[] args) {
        MapFuseS mapFuseS = new MapFuseS();
        MapFuseS1 mapFuseS1 = new MapFuseS1();
        MapFuseS1 mapFuseS2 = new MapFuseS1();
        MapFuse mapFuse = new MapFuse();
        mapFuse.testInvokeAnonymity();
    }
}
