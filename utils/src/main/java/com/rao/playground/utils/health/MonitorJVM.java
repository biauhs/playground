package com.rao.playground.utils.health;

import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.UnixOperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by shuaibrao on 29/03/2017.
 */
public class MonitorJVM {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorJVM.class);

    public static void monitor(ServerHealth serverHealth) {
        monitorFileHandles(serverHealth);
        monitorMemory(serverHealth);
    }

    public static void monitorFileHandles(ServerHealth serverHealth) {

        getUnixOSMXBean(os -> {
            LOGGER.debug("Max number of open fd: " + os.getMaxFileDescriptorCount());
            serverHealth.setMaxOpenFileHandles(os.getMaxFileDescriptorCount());
        });

        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(() -> getUnixOSMXBean(os -> {
                    LOGGER.debug("Number of open fd: " + os.getOpenFileDescriptorCount());
                    serverHealth.setOpenFileHandles(os.getOpenFileDescriptorCount());
                }),
                        0,
                        1,
                        TimeUnit.MINUTES);

    }

    private static void getUnixOSMXBean(Consumer<UnixOperatingSystemMXBean> consumer) {
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        if (os instanceof UnixOperatingSystemMXBean) {
            consumer.accept((UnixOperatingSystemMXBean) os);
        }
    }

    public static void monitorMemory(ServerHealth serverHealth) {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            String GEN = "PS Old Gen";
            GarbageCollectorMXBean bean = ManagementFactory.newPlatformMXBeanProxy(mbs, "java.lang:type=GarbageCollector,name=PS MarkSweep", GarbageCollectorMXBean.class);

            long max = ManagementFactory.getMemoryPoolMXBeans()
                    .stream()
                    .filter(mplMXBean -> mplMXBean.getName().equals(GEN))
                    .mapToLong(m -> m.getCollectionUsage().getMax())
                    .findFirst().orElse(-1);
            serverHealth.setOldGenTotalMemory(max);

            mbs.addNotificationListener(bean.getObjectName(), (notification, handback) -> {
                           long usedBefore = bean.getLastGcInfo().getMemoryUsageBeforeGc().get(GEN).getUsed();
                           long usedAfter = bean.getLastGcInfo().getMemoryUsageAfterGc().get(GEN).getUsed();
                           double recovered = (usedAfter) / (double)max;

                           serverHealth.setOldGenMemoryBeforeLastFullGC(usedBefore);
                           serverHealth.setOldGenMemoryAfterLastFullGC(usedAfter);
                           serverHealth.setPercentageOldGenMemoryUsed(recovered * 100);
                           serverHealth.setOldGenLessThan90Percent(recovered < 0.9d);

            }, null, null);

        } catch (Exception e) {
            LOGGER.info("Unable to monitor memory", e);
        }
    }

}
