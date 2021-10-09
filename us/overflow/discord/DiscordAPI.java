// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.discord;

import us.overflow.base.Check;
import us.overflow.base.user.User;
import club.minnced.discord.webhook.send.WebhookEmbed;
import java.awt.Color;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.WebhookClient;
import us.overflow.Overflow;

public class DiscordAPI
{
    private String URL;
    
    public DiscordAPI() {
        this.URL = null;
    }
    
    public void connect() {
        this.URL = Overflow.getInstance().getConfigValues().getDiscordBotToken();
    }
    
    public void disconnect() {
    }
    
    public void postMessage(final String header, final String body) {
        if (Overflow.getInstance().getConfigValues().isDiscordEnabled()) {
            final WebhookClient webhookClient;
            final WebhookEmbed embed;
            Overflow.getInstance().getDiscordPostExecutor().execute(() -> {
                webhookClient = WebhookClient.withUrl(this.URL);
                embed = new WebhookEmbedBuilder().setColor(Integer.valueOf(this.getIntFromColor(Color.GREEN))).setTitle(new WebhookEmbed.EmbedTitle(header, (String)null)).setDescription(body).build();
                webhookClient.send(embed, new WebhookEmbed[0]);
                webhookClient.close();
            });
        }
    }
    
    public void postBan(final User user) {
        if (Overflow.getInstance().getConfigValues().isDiscordEnabled()) {
            final String header = this.formatString(Overflow.getInstance().getConfigValues().getDiscordMessageHeader(), user);
            this.postMessage(header, user.getPlayer().getName() + " was banned!");
        }
    }
    
    public void postCheckFlag(final User user, final Check check) {
        if (Overflow.getInstance().getConfigValues().isDiscordEnabled()) {
            final String header = this.formatString(Overflow.getInstance().getConfigValues().getDiscordMessageHeader(), user, check);
            final String body = this.formatString(Overflow.getInstance().getConfigValues().getDiscordMessageBody(), user, check);
            this.postMessage(header, body);
        }
    }
    
    private String formatString(final String in, final User user, final Check check) {
        return in.replace("%PLAYER%", user.getPlayer().getName()).replace("%CHECK%", check.getCheckName()).replace("%TYPE%", check.getType()).replace("%VL%", String.valueOf(user.getViolation()));
    }
    
    private String formatString(final String in, final User user) {
        return in.replace("%PLAYER%", user.getPlayer().getName()).replace("%VL%", String.valueOf(user.getViolation()));
    }
    
    private int getIntFromColor(final Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        red = (red << 16 & 0xFF0000);
        green = (green << 8 & 0xFF00);
        blue &= 0xFF;
        return 0xFF000000 | red | green | blue;
    }
    
    public String getURL() {
        return this.URL;
    }
    
    public void setURL(final String URL) {
        this.URL = URL;
    }
}
