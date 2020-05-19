package com.chen.accessibilityservice;

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/13
 *     desc   : 步骤
 *     version: 1.0
 * </pre>
 */
public class Step {

    private Step() {
    }

    public static Step getInstance() {
        return StepHolder.instance;
    }

    private static class StepHolder {
        private static final Step instance = new Step();
    }

    /**
     * 阶段常量
     */
    public static final int FETCHING_STAGE = 0, OPENING_STAGE = 1, FETCHED_STAGE = 2, OPENED_STAGE = 3;

    /**
     * 当前阶段
     */
    private int currentStage = FETCHED_STAGE;

    /**
     * 阶段互斥，不允许多次回调进入同一阶段
     */
    public boolean mutex = false;


    /**
     * 记录接下来的阶段
     *
     * @param _stage
     */
    public void entering(int _stage) {
        getInstance().currentStage = _stage;
        mutex = false;
    }

    /**
     * 记录当前的阶段
     */
    public int getCurrentStage() {
        return getInstance().currentStage;
    }
}
