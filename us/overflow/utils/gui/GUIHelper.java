// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.gui;

import us.overflow.gui.sub.ToggleChecksGUI;
import us.overflow.gui.sub.CheckTypeGUI;
import us.overflow.gui.OverflowGUI;

public class GUIHelper
{
    private OverflowGUI overflowGUI;
    private CheckTypeGUI checkTypeGUI;
    private ToggleChecksGUI toggleChecksGUI;
    
    public GUIHelper() {
        this.overflowGUI = new OverflowGUI();
        this.checkTypeGUI = new CheckTypeGUI();
        this.toggleChecksGUI = new ToggleChecksGUI();
    }
    
    public OverflowGUI getOverflowGUI() {
        return this.overflowGUI;
    }
    
    public CheckTypeGUI getCheckTypeGUI() {
        return this.checkTypeGUI;
    }
    
    public ToggleChecksGUI getToggleChecksGUI() {
        return this.toggleChecksGUI;
    }
}
