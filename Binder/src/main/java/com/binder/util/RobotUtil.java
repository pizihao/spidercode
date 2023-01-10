package com.binder.util;

import sun.rmi.runtime.RuntimeUtil;

import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class RobotUtil {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(10, 1000);
        RuntimeUtil.GetInstanceAction instanceAction = new RuntimeUtil.GetInstanceAction();
        RuntimeUtil run = instanceAction.run();
        ScheduledThreadPoolExecutor scheduler = run.getScheduler();
    }

}
