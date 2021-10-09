// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.processors;

import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.tinyprotocol.packet.out.WrappedOutVelocityPacket;
import java.util.HashSet;
import org.bukkit.Material;
import java.util.Set;
import us.overflow.Overflow;
import us.overflow.base.user.User;

public class CombatProcessor
{
    private User user;
    
    public void update(final Object packet, final String type) {
        if (type.equalsIgnoreCase("PacketPlayInArmAnimation")) {
            if (Overflow.getInstance().getVersion() == Overflow.Version.V1_8) {
                if (this.user.getPlayer().getTargetBlock((Set)null, 5).getType() != Material.AIR) {
                    this.user.isBreakingAFUCKINGBLOCK = true;
                }
                else if (this.user.getPlayer().getTargetBlock((Set)null, 5).getType() == Material.AIR) {
                    this.user.isBreakingAFUCKINGBLOCK = false;
                }
            }
            if (Overflow.getInstance().getVersion() == Overflow.Version.V1_7) {
                if (this.user.getPlayer().getTargetBlock((HashSet)null, 5).getType() != Material.AIR) {
                    this.user.isBreakingAFUCKINGBLOCK = true;
                }
                else if (this.user.getPlayer().getTargetBlock((HashSet)null, 5).getType() == Material.AIR) {
                    this.user.isBreakingAFUCKINGBLOCK = false;
                }
            }
        }
        if (type.equalsIgnoreCase("PacketPlayOutEntityVelocity")) {
            final WrappedOutVelocityPacket wrappedOutVelocityPacket = new WrappedOutVelocityPacket(packet, this.user.getPlayer());
            if (wrappedOutVelocityPacket.getId() == this.user.getPlayer().getEntityId()) {
                this.user.setLastEntityVelocity(System.currentTimeMillis());
                this.user.setKBTicks(0);
            }
        }
        if (type.equalsIgnoreCase("PacketPlayInKeepAlive")) {
            this.user.setPing((int)(System.currentTimeMillis() - this.user.getLastServerKeepAlive()));
        }
        if (type.equalsIgnoreCase("PacketPlayInUseEntity")) {
            final WrappedInUseEntityPacket entityPacket = new WrappedInUseEntityPacket(packet, this.user.getPlayer());
            if (entityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK) {
                if (!this.user.attacksSet) {
                    this.user.attacksSet = true;
                    this.user.lastAttacksSet = System.currentTimeMillis();
                    this.user.attacks = 0;
                    this.user.aimQAttacks = 0;
                }
                if (this.user.attacksSet && System.currentTimeMillis() - this.user.lastAttacksSet > 1000L && System.currentTimeMillis() - this.user.lastAttackPacket > 1000L) {
                    this.user.attacksSet = false;
                    this.user.attacks = 0;
                    this.user.aimQAttacks = 0;
                }
                if (this.user.lastEntityAttacked != null) {
                    if (this.user.lastEntityAttacked != entityPacket.getEntity()) {
                        this.user.constantEntityTicks = 0;
                    }
                    else {
                        final User user = this.user;
                        ++user.constantEntityTicks;
                    }
                }
                final User user2 = this.user;
                ++user2.attacks;
                final User user3 = this.user;
                ++user3.aimQAttacks;
                this.user.lastAttackPacket = System.currentTimeMillis();
                this.user.lastEntityAttacked = entityPacket.getEntity();
                if (entityPacket.getEntity() instanceof Player) {
                    final User targetUser = Overflow.getInstance().getUserManager().getUser(entityPacket.getEntity().getUniqueId());
                    if (targetUser != null) {
                        targetUser.lastAttackedByPlayer = System.currentTimeMillis();
                    }
                }
            }
        }
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
}
