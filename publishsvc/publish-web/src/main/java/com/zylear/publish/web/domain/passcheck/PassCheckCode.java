package com.zylear.publish.web.domain.passcheck;

import java.util.Date;

public class PassCheckCode {

//    public static final Set<String> PLUGIN_KEY_SET
//            = new HashSet<>(Arrays.asList("hosts", "start_plugin", "repair_pubg", "v2_hosts", "v2_start_plugin", "v2_repair_pubg"));
//
//    public static final Set<String> PASS_CHECK_SET
//            = new HashSet<>(Arrays.asList("init.sh", "build.prop", "pass_check", "stop_pass_check","v2_init.sh", "v2_build.prop", "v2_pass_check", "v2_stop_pass_check"));

    private Integer id;

    private String configKey;

    private String remark;

    private Byte isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    private String configValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}