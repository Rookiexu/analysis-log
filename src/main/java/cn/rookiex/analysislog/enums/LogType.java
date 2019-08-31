package cn.rookiex.analysislog.enums;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
public enum LogType {
    RunTime("runTime");

    String value;

    LogType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
