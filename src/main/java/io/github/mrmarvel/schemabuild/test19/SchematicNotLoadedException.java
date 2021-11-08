package io.github.mrmarvel.schemabuild.test19;

/**
 * Called when a schematic's data has not been loaded.
 */
public class SchematicNotLoadedException extends Exception {

    public SchematicNotLoadedException(String message) {
        super(message);
    }
}
