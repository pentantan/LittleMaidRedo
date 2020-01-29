package mmr.maidmodredo.entity.data;

import mmr.maidmodredo.init.LittleSchedules;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class MaidJob {
    public static final DefaultedRegistry<MaidJob> MAID_JOB_REGISTRY = registerDefaulted("maid_job", "normal", () -> {
        return MaidJob.NORMAL;
    });
    public static final MaidJob NORMAL = new MaidJob("normal", PointOfInterestType.UNEMPLOYED, (item) -> {
        return item == ItemStack.EMPTY;
    }).setSchedule(LittleSchedules.WILDMAID);
    public static final MaidJob FENCER = new MaidJob("fencer", PointOfInterestType.UNEMPLOYED, (item) -> {
        return item.getItem() instanceof SwordItem;
    });

    private final String name;
    private final PointOfInterestType pointOfInterest;
    private final Predicate<ItemStack> field_221168_r;
    private Schedule schedule;

    public MaidJob(String nameIn, PointOfInterestType pointOfInterestIn, Predicate<ItemStack> p_i50179_3_) {
        this.name = nameIn;
        this.pointOfInterest = pointOfInterestIn;
        this.field_221168_r = p_i50179_3_;
    }

    public PointOfInterestType getPointOfInterest() {
        return this.pointOfInterest;
    }

    public Predicate<ItemStack> getRequireItem() {
        return this.field_221168_r;
    }

    public String toString() {
        return this.name;
    }

    public MaidJob setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }


    private static <T> DefaultedRegistry<T> registerDefaulted(String p_222933_0_, String p_222933_1_, Supplier<T> p_222933_2_) {
        return register(p_222933_0_, new DefaultedRegistry<>(p_222933_1_), p_222933_2_);
    }

    private static <T, R extends MutableRegistry<T>> R register(String p_222939_0_, R p_222939_1_, Supplier<T> p_222939_2_) {
        ResourceLocation resourcelocation = new ResourceLocation(p_222939_0_);
        return (R) (Registry.REGISTRY.register(resourcelocation, p_222939_1_));
    }
}
