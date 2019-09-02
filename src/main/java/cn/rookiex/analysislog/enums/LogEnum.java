package cn.rookiex.analysislog.enums;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
public enum LogEnum {
    RESOURCE_LOG("resource"),RUN_LONG_TIME("runLongTime"),SYSTEM_EX_LOG("systemEx"),SYSTEM_INFO("systemInfo");
    String value;

    LogEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
