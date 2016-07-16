package io.github.teamdevintia.devathlon3.enums;

/**
 * @author Shad0wCore
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

    public String source() {
        return this.source;
    }

}
