package com.amazon.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    protected static Logger logger = LogManager.getLogger(LogUtils.class);
    protected static ThreadLocal<Integer> noOfSteps = new ThreadLocal<Integer>();

    public static Integer getNoOfSteps() {

        if (noOfSteps.get() == null) {
            noOfSteps.set(0);
        }
        return noOfSteps.get();
    }

    public static void printSteps(String log) {

        int currentStep = getNoOfSteps() + 1;
        logger.info(new StringBuilder(" ").append("STEP  " + currentStep + " :" + log).toString());

    }

}
