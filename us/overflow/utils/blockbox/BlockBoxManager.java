// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox;

import us.overflow.utils.blockbox.boxes.BlockBox1_8_R3;
import us.overflow.utils.blockbox.boxes.BlockBox1_8_R2;
import us.overflow.utils.blockbox.boxes.BlockBox1_8_R1;
import us.overflow.utils.blockbox.boxes.BlockBox1_7_R4;
import us.overflow.tinyprotocol.api.ProtocolVersion;

public class BlockBoxManager
{
    private BlockBox blockBox;
    
    public BlockBoxManager() {
        final String version = ProtocolVersion.getGameVersion().getServerVersion().replaceAll("v", "");
        if (version.equalsIgnoreCase("1_7_R4")) {
            this.blockBox = (BlockBox)new BlockBox1_7_R4();
        }
        else if (version.equalsIgnoreCase("1_8_R1")) {
            this.blockBox = (BlockBox)new BlockBox1_8_R1();
        }
        else if (version.equalsIgnoreCase("1_8_R2")) {
            this.blockBox = (BlockBox)new BlockBox1_8_R2();
        }
        else if (version.equalsIgnoreCase("1_8_R3")) {
            this.blockBox = (BlockBox)new BlockBox1_8_R3();
        }
    }
    
    public BlockBox getBlockBox() {
        return this.blockBox;
    }
}
