package org.sniff.mode;

import java.util.Date;

public class StaffBuilder {
    private Date createTime;
    private Long id;
    private String name;
    private Double wage;

    public StaffBuilder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public StaffBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public StaffBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StaffBuilder setWage(Double wage) {
        this.wage = wage;
        return this;
    }

    public Staff createStaff() {
        return new Staff(id, name, wage, createTime);
    }
}