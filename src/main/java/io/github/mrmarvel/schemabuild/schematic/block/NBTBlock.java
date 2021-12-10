package io.github.mrmarvel.schemabuild.schematic.block;

import io.github.mrmarvel.schemabuild.tests.test19.WrongIdException;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.block.BlockState;
import org.bukkit.util.Vector;

public abstract class NBTBlock {
    
    private final NBTTagCompound nbtTag;
    
    public NBTBlock(NBTTagCompound nbtTag) {
        this.nbtTag = nbtTag;
    }

    public NBTTagCompound getNbtTag() {
        return nbtTag;
    }

    public Vector getOffset() {
        NBTTagCompound compound = this.getNbtTag();
        int[] pos = compound.getIntArray("Pos");
        return new Vector(pos[0], pos[1], pos[2]);
    }

    public abstract void setData(BlockState state) throws WrongIdException;

    public abstract boolean isEmpty();
}
