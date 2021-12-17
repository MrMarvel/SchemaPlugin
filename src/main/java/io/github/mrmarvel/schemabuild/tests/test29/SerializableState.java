package io.github.mrmarvel.schemabuild.tests.test29;

import io.github.mrmarvel.schemabuild.tests.test17.PluginState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

@AllArgsConstructor
public class SerializableState implements Serializable {
    @NonNull
    @Getter
    @Setter
    private PluginState state;

    private static final long serialVersionUUID = 1L;

    @Override
    public String toString() {
        return "SerializableState{" +
                "state=" + state +
                '}';
    }
    public static String serialize(SerializableState ss) throws IOException {
        return toString(ss);
    }

    public static SerializableState deserialize(String msg) throws IOException, ClassNotFoundException {
        return (SerializableState) fromString(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableState that = (SerializableState) o;
        return state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    /** Read the object from Base64 string. */
    private static Object fromString( String s ) throws IOException,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
