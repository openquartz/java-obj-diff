package com.openquartz.javaobjdiff.test.bean;

public enum Sex implements SelfEnumInterface<Integer> {

    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer code;

    private String desc;

    Sex(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
