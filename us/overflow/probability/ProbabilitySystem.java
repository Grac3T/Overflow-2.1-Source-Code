// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.probability;

import java.util.ArrayList;
import us.overflow.base.Check;
import java.util.List;

public class ProbabilitySystem
{
    private List<Check> flaggedChecks;
    private int totalVL;
    private double partVL;
    
    public ProbabilitySystem() {
        this.flaggedChecks = new ArrayList();
    }
    
    public double getChance() {
        return (this.partVL + this.flaggedChecks.size()) % this.totalVL;
    }
    
    public Prob getProb() {
        final double c = this.getChance();
        if (c < 1.2) {
            return Prob.COULD_BE;
        }
        if (c > 1.2 && c < 2.0) {
            return Prob.MIGHT_BE;
        }
        if (c > 2.0 && c < 3.0) {
            return Prob.LIKELY;
        }
        if (c > 3.0) {
            return Prob.USING;
        }
        return Prob.COULD_BE;
    }
    
    public List<Check> getFlaggedChecks() {
        return (List<Check>)this.flaggedChecks;
    }
    
    public int getTotalVL() {
        return this.totalVL;
    }
    
    public double getPartVL() {
        return this.partVL;
    }
    
    public void setFlaggedChecks(final List<Check> flaggedChecks) {
        this.flaggedChecks = flaggedChecks;
    }
    
    public void setTotalVL(final int totalVL) {
        this.totalVL = totalVL;
    }
    
    public void setPartVL(final double partVL) {
        this.partVL = partVL;
    }
    
    public enum Prob
    {
        public static final Prob COULD_BE;
        public static final Prob MIGHT_BE;
        public static final Prob LIKELY;
        public static final Prob USING;
        
        public static Prob valueOf(final String name) {
            return Enum.valueOf(Prob.class, name);
        }
        
        static {
            Prob.COULD_BE = new Prob("COULD_BE", 0);
            Prob.MIGHT_BE = new Prob("MIGHT_BE", 1);
            Prob.LIKELY = new Prob("LIKELY", 2);
            Prob.USING = new Prob("USING", 3);
            Prob.$VALUES = new Prob[] { Prob.COULD_BE, Prob.MIGHT_BE, Prob.LIKELY, Prob.USING };
        }
    }
}
