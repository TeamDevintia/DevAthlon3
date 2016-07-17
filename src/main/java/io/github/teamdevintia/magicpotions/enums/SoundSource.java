package io.github.teamdevintia.magicpotions.enums;

/**
 * @author Shad0wCore
 *         <p>
 *         A collection of sound catorgories.
 *         <p>
 *         <b>Description from Minecraft Wiki:</b>
 *         The category this sound event belongs to. Valid category names are
 *         "ambient", "weather", "player", "neutral", "hostile", "block", "record", "music", "master" and "voice".
 *         This String lets the sound system know what sound events belong to what category, so the volume can
 *         be adjusted based on what the sound options are set to for each category.
 */
public enum SoundSource {

    MASTER("master"),
    MUSIC("music"),
    RECORD("record"),
    WEATHER("weather"),
    BLOCK("block"),
    HOSTILE("hostile"),
    NEUTRAL("neutral"),
    PLAYER("player"),
    AMBIENT("ambient"),
    VOICE("voice");

    private String source;

    SoundSource(String source) {
        this.source = source;
    }

    /**
     * @return the name of this source
     */
    public String source() {
        return this.source;
    }

}
