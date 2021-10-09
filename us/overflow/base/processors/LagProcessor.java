// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.processors;

import us.overflow.tinyprotocol.packet.in.WrappedInTransactionPacket;
import us.overflow.tinyprotocol.api.TinyProtocolHandler;
import us.overflow.tinyprotocol.packet.out.WrappedOutTransaction;
import us.overflow.tinyprotocol.packet.out.WrappedOutAttachEntity;
import us.overflow.utils.other.TimeUtils;
import us.overflow.Overflow;
import us.overflow.base.user.User;

public class LagProcessor
{
    private User user;
    
    public void update(final Object packet, final String type) {
        if ((type.equalsIgnoreCase("PacketPlayInPosition") || type.equalsIgnoreCase("PacketPlayInPositionLook") || type.equalsIgnoreCase("PacketPlayInLook") || type.equalsIgnoreCase("PacketPlayInFlying")) && Overflow.getInstance().getConfigValues().isUseBackupLagCheck()) {
            if (System.currentTimeMillis() - this.user.lastSkippedFlying > 85L) {
                if (this.user.fullTickSkipped < 20) {
                    final User user = this.user;
                    user.fullTickSkipped += 5;
                }
            }
            else if (this.user.fullTickSkipped > 0) {
                final User user2 = this.user;
                --user2.fullTickSkipped;
            }
            if (this.user.fullTickSkipped > 5) {
                if (this.user.packetsSkipped < 200) {
                    final User user3 = this.user;
                    user3.packetsSkipped += 20;
                }
            }
            else if (this.user.packetsSkipped > 0) {
                final User user4 = this.user;
                --user4.packetsSkipped;
            }
            final boolean isPossiblyLagging = this.user.packetsSkipped > 9;
            if (isPossiblyLagging) {
                this.user.lastSkippedTicksLag = System.currentTimeMillis();
            }
            if (TimeUtils.secondsFromLong(this.user.lastSkippedTicksLag) < 5L) {
                this.user.setLagging(true);
                this.user.setLastLag(System.currentTimeMillis());
            }
            else {
                this.user.setLagging(false);
            }
            this.user.lastSkippedFlying = System.currentTimeMillis();
        }
        if (type.equalsIgnoreCase("PacketPlayOutAttachEntity")) {
            final WrappedOutAttachEntity attach = new WrappedOutAttachEntity(packet, this.user.getPlayer());
            this.user.getGlobalObject().setRiding(attach.getC() != -1);
        }
        if (type.equalsIgnoreCase("PacketPlayOutKeepAlive")) {
            this.user.setLastServerKeepAlive(System.currentTimeMillis());
            if (!Overflow.getInstance().getConfigValues().isUseBackupLagCheck() && Overflow.getInstance().getConfigValues().isSecondLagFix()) {
                final WrappedOutTransaction transaction = new WrappedOutTransaction(0, (short)420, false);
                TinyProtocolHandler.sendPacket(this.user.getPlayer(), transaction.getObject());
            }
        }
        if (type.equalsIgnoreCase("PacketPlayOutTransaction")) {
            final WrappedOutTransaction wrappedInTransactionPacket = new WrappedOutTransaction(packet, this.user.getPlayer());
            if (wrappedInTransactionPacket.getAction() == 420 || (wrappedInTransactionPacket.getAction() == 421 && !Overflow.getInstance().getConfigValues().isSecondLagFix())) {
                this.user.lastServerTransaction = System.currentTimeMillis();
            }
            if (wrappedInTransactionPacket.getAction() == 421) {
                this.user.lastServerTransaction2 = System.currentTimeMillis();
            }
        }
        if (type.equalsIgnoreCase("PacketPlayInTransaction")) {
            final WrappedInTransactionPacket wrappedInTransactionPacket2 = new WrappedInTransactionPacket(packet, this.user.getPlayer());
            if (wrappedInTransactionPacket2.getAction() == 1337 && Overflow.getInstance().getConfigValues().isKeepAliveFix()) {
                final int ping = (int)(System.currentTimeMillis() - this.user.lastTransShit);
                this.user.lastCalulated = this.user.calculatedPing;
                this.user.calculatedPing = System.currentTimeMillis() - this.user.lastTransShit;
                this.user.setPing(ping);
            }
            if (wrappedInTransactionPacket2.getAction() == 421) {
                final long diff = Math.abs(System.currentTimeMillis() - this.user.lastServerTransaction2);
                final long diff2 = diff - this.user.lastTransaction2;
                if (diff2 > 70L) {
                    this.user.setLaggin2(true);
                    this.user.setLastLag2(System.currentTimeMillis());
                }
                else {
                    this.user.setLaggin2(false);
                }
                this.user.lastTransaction2 = diff;
            }
            if (wrappedInTransactionPacket2.getAction() == 420) {
                this.user.lastTransactionPing = this.user.transactionPing;
                this.user.transactionPing = System.currentTimeMillis() - this.user.lastServerTransaction;
                this.user.lastClientTransaction = System.currentTimeMillis();
                this.user.transactionLast = Math.abs(this.user.transactionPing - this.user.lastTransactionPing);
                this.user.lastLastFucker = Math.abs(this.user.transactionLast - Math.abs(this.user.transactionPing - this.user.lastTransactionPing));
                this.user.calculatedRealPing = (int)Math.abs(this.user.transactionPing - this.user.lastTransactionPing);
                this.user.lastTransactionReciv = System.currentTimeMillis();
                this.user.lastTransUpdate = System.currentTimeMillis();
                if (!Overflow.getInstance().getConfigValues().isKeepAliveFix()) {
                    this.user.lastCalulated = this.user.calculatedPing;
                    this.user.calculatedPing = System.currentTimeMillis() - this.user.lastServerKeepAlive;
                }
            }
            if (Math.abs(this.user.transactionPing - this.user.lastTransactionPing) > 70L) {
                this.user.setLagging(true);
                this.user.setLastLag(System.currentTimeMillis());
            }
            else {
                this.user.setLagging(false);
            }
        }
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
}
