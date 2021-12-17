package io.github.mrmarvel.schemabuild.tests.test28;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public class StructureT {
    @Getter
    @NonNull
    private String name;
    private Schema sc;
    public StructureT(@NotNull String name, String filename) {
        this.name = name;
        sc = new Schema(filename) { // анонимный класс на базе Вложенного нестатического
            @Override
            public void test() {
                StructureT.this.name = "TEST"+name;
            }
        };
    }
    @AllArgsConstructor
    class Schema {// Вложенный нестатический класс
        @NonNull
        private String filename;
        public void test() {
            StructureT.this.name = name; // Специально обращаюсь к старшему классу,
            // чтоб оправдатьнестатический вид, иначе можно было бы сделать его статическим.
        }
    }
}
