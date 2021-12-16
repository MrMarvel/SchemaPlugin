package io.github.mrmarvel.schemabuild.tests.test24;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemNameCheckerT {
    public static boolean checkName(String name) {
        String regex = "\\D\\W*+";//Поиск идентфикатора по СВЕРХЖАДНОМУ алгоритму
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(name);
        return mat.matches();
    }
}
