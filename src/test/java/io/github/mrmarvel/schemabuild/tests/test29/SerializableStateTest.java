package io.github.mrmarvel.schemabuild.tests.test29;

import io.github.mrmarvel.schemabuild.tests.test17.PluginState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SerializableStateTest {

    @Test
    void serialization_deserialization() {
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
}