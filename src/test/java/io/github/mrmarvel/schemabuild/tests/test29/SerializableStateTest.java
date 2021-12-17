package io.github.mrmarvel.schemabuild.tests.test29;

import io.github.mrmarvel.schemabuild.tests.test17.PluginState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SerializableStateTest {

    @Test
    void serialization_TEST() {
        SerializableState state = new SerializableState(PluginState.ENABLED);
        String ser = null;
        SerializableState deserializedState = null;
        try {
            ser = SerializableState.serialize(state);
            deserializedState = SerializableState.deserialize(ser);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(state, deserializedState);
    }

    @Test
    void serialize_NULL() throws IOException {
        try {
            SerializableState.serialize(new SerializableState(null));
        } catch (NullPointerException e) {
            return;
        }
        Assertions.fail("Позволило инициализировать значением null");
    }
}