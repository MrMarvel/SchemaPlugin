package io.github.mrmarvel.schemabuild.tests.test18;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionTest {
    public static void test(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        fis.close();
    }
}
