//package com.younger;
///**
// * 秒表类，用于计算执行时间
// * 注意该类是非线程安全的
// * @author jxqlovejava
// *
// */
//public class StopWatch {
//    
//    private static final String DEFAULT_TASK_NAME = "defaultTask";
//    private String taskName;
//    private long start, end;
//    private boolean hasStarted, hasEnded;
//    
//    // 时间单位枚举：毫秒、秒和分钟
//    public enum TimeUnit { MILLI, SECOND, MINUTE  }
//    
//    public StopWatch() {
//        this(DEFAULT_TASK_NAME);
//    }
//    
//    public StopWatch(String taskName) {
//        this.taskName = (taskName.isEmpty()) ? DEFAULT_TASK_NAME : taskName;
//    }
//    
//    public void start() {
//        start = System.currentTimeMillis();
//        hasStarted = true;
//    }
//    
//    public void end() throws Exception {
//        if(!hasStarted) {
//            throw new Exception("调用StopWatch的end()方法之前请先调用start()方法");
//        }
//        end = System.currentTimeMillis();
//        hasEnded = true;
//    }
//    
//    public void clear() {
//        this.start = 0;
//        this.end = 0;
//        
//        this.hasStarted = false;
//        this.hasEnded = false;
//    }
//    
//    /**
//     * 获取总耗时，单位为毫秒
//     * @return 消耗的时间，单位为毫秒
//     * @throws Exception 
//     */
//    public long getEclapsedMillis() throws Exception {
//        if(!hasEnded) {
//            throw new Exception("请先调用end()方法");
//        }
//        
//        return (end-start);
//    }
//    
//    /**
//     * 获取总耗时，单位为秒
//     * @return 消耗的时间，单位为秒
//     * @throws Exception 
//     */
//    public long getElapsedSeconds() throws Exception {
//        return this.getEclapsedMillis() / 1000;
//    }
//    
//    /**
//     * 获取总耗时，单位为分钟
//     * @return 消耗的时间，单位为分钟
//     * @throws Exception 
//     */
//    public long getElapsedMinutes() throws Exception {
//        return this.getEclapsedMillis() / (1000*60);
//    }
//    
//    public void setTaskName(String taskName) {
//        this.taskName =(taskName.isEmpty()) ? DEFAULT_TASK_NAME : taskName;
//    }
//    
//    public String getTaskName() {
//        return this.taskName;
//    }
//    
//    /**
//     * 输出任务耗时情况，单位默认为毫秒
//     * @throws Exception 
//     */
//    public void printEclapseDetail() throws Exception {
//        this.printEclapseDetail(TimeUnit.MILLI);
//    }
//    
//    /**
//     * 输出任务耗时情况，可以指定毫秒、秒和分钟三种时间单位
//     * @param timeUnit 时间单位
//     * @throws Exception 
//     */
//    public void printEclapseDetail(TimeUnit timeUnit) throws Exception {
//        switch(timeUnit) {
//        case MILLI:
//            System.out.println(this.getTaskName() + "任务耗时（毫秒）：" + this.getEclapsedMillis());
//            break;
//        case SECOND:
//            System.out.println(this.getTaskName() + "任务耗时（秒）：" + this.getElapsedSeconds());
//            break;
//        case MINUTE:
//            System.out.println(this.getTaskName() + "任务耗时（分钟）：" + this.getElapsedMinutes());
//            break;
//        default:
//            System.out.println(this.getTaskName() + "任务耗时（毫秒）：" + this.getEclapsedMillis());
//        }
//    }
//
//}