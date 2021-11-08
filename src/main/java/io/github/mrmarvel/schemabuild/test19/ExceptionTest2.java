package io.github.mrmarvel.schemabuild.test19;

public class ExceptionTest2 {
    public static void test() throws SchematicNotLoadedException {
        throw new SchematicNotLoadedException("bad thing");
    }
}
