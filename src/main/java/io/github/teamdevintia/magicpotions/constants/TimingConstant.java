package io.github.teamdevintia.magicpotions.constants;

import io.github.teamdevintia.magicpotions.MagicPotions;

import java.util.Map;

/**
 * This constant stores all timings that are used to delay certain actions
 *
 * @author MiniDigger
 */
public class TimingConstant extends Constant<Long> {

    public TimingConstant(MagicPotions instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("spawn.throne.step1.delay", (long) (20 * 1.5));
        this.getContentMap().put("spawn.throne.step2.delay", (long) (20 * 3));
        this.getContentMap().put("spawn.throne.step3.delay", (long) (20 * 4.5));
        this.getContentMap().put("spawn.throne.step4.delay", (long) (20 * 5));
        this.getContentMap().put("spawn.throne.step5.delay", (long) (20 * (5 + 10 + 10))); // 5: step before, 12: last message, 10: time to tick
        this.getContentMap().put("spawn.throne.step4.message1.delay", (long) (20 * 2));
        this.getContentMap().put("spawn.throne.step4.message2.delay", (long) (20 * 4));
        this.getContentMap().put("spawn.throne.step4.message3.delay", (long) (20 * 6));
        this.getContentMap().put("spawn.throne.step4.message4.delay", (long) (20 * 8));
        this.getContentMap().put("spawn.throne.step4.message5.delay", (long) (20 * 10));
    }

    @Override
    public Long get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public Map<String, Long> getContentMap() {
        return super.getContentMap();
    }
}