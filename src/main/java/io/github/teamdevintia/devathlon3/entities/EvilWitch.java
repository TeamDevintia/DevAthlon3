package io.github.teamdevintia.devathlon3.entities;

import io.github.teamdevintia.devathlon3.Devathlon3;
import net.minecraft.server.v1_10_R1.EntityLiving;
import net.minecraft.server.v1_10_R1.EntityPotion;
import net.minecraft.server.v1_10_R1.EntityWitch;
import net.minecraft.server.v1_10_R1.ItemStack;
import net.minecraft.server.v1_10_R1.MathHelper;
import net.minecraft.server.v1_10_R1.SoundEffects;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;

/**
 * A evil version of the already pretty annoying mob, throw with magic potions
 */
public class EvilWitch extends EntityWitch {

    public EvilWitch(World world) {
        super(world);
    }

    // attackEntityWithRangedAttack
    public void a(EntityLiving var1, float var2) {
        // if not drinking potion
        if (!this.o()) {
            // calc dir
            double yOffset = var1.locY + (double) var1.getHeadHeight() - 1.100000023841858D;
            double x = var1.locX + var1.motX - this.locX;
            double y = yOffset - this.locY;
            double z = var1.locZ + var1.motZ - this.locZ;
            float distance = MathHelper.sqrt(x * x + z * z);

            ItemStack potion = CraftItemStack.asNMSCopy(Devathlon3.getInstance().getPotionManager().getRandomPotion());
            System.out.println(potion.getName() + " selcted");
            // spawn
            EntityPotion potionEntity = new EntityPotion(this.world, this, potion);
            potionEntity.pitch -= -20.0F;
            potionEntity.shoot(x, y + (double) (distance * 0.2F), z, 0.75F, 8.0F);
            this.world.a(null, this.locX, this.locY, this.locZ, SoundEffects.gT, this.bC(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            this.world.addEntity(potionEntity);
        }
    }
}
