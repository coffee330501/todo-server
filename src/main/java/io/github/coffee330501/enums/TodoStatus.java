package io.github.coffee330501.enums;

public enum TodoStatus implements BaseEnum {
    Pending("处理中"),
    Closed("已完成"),
    Deleted("已删除"),
    ;

    private String desc;

    TodoStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
