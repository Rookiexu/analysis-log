package cn.rookiex.analysislog.enums;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe : RUN_LONG_TIME,会记录方法执行超长的日志,SYSTEM_EX_LOG,会记录出现异常的方法,COUNT_LOG,会记录所有的调用记录
 */
public enum LogEnum {
    RESOURCE_LOG("resource"),RUN_LONG_TIME("runLongTime"),SYSTEM_EX_LOG("systemEx"),SYSTEM_INFO("systemInfo"),COUNT_LOG("countLog");
    String value;

    LogEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
