package com.rao.playground.utils.health;

/**
 * Created by shuaibrao on 29/03/2017.
 */
public class ServerHealth {

    private long openFileHandles;
    private long oldGenTotalMemory;
    private long oldGenMemoryBeforeLastFullGC;
    private long oldGenMemoryAfterLastFullGC;
    private double percentageOldGenMemoryUsed;
    private boolean oldGenLessThan90Percent;
    private long maxFileDescriptorCount;

    public void setMaxOpenFileHandles(long maxFileDescriptorCount) {
        this.maxFileDescriptorCount = maxFileDescriptorCount;
    }

    public void setOpenFileHandles(long openFileHandles) {
        this.openFileHandles = openFileHandles;
    }

    public void setOldGenTotalMemory(long oldGenTotalMemory) {
        this.oldGenTotalMemory = oldGenTotalMemory;
    }

    public void setOldGenMemoryBeforeLastFullGC(long oldGenMemoryBeforeLastFullGC) {
        this.oldGenMemoryBeforeLastFullGC = oldGenMemoryBeforeLastFullGC;
    }

    public void setOldGenMemoryAfterLastFullGC(long oldGenMemoryAfterLastFullGC) {
        this.oldGenMemoryAfterLastFullGC = oldGenMemoryAfterLastFullGC;
    }

    public void setPercentageOldGenMemoryUsed(double percentageOldGenMemoryUsed) {
        this.percentageOldGenMemoryUsed = percentageOldGenMemoryUsed;
    }

    public void setOldGenLessThan90Percent(boolean oldGenLessThan90Percent) {
        this.oldGenLessThan90Percent = oldGenLessThan90Percent;
    }
}
