package org.sniff.mode;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * @auth snifferhu
 * @date 2017/4/20 22:27
 */
public class Staff {
    private Long id;
    private String name;
    private Double wage;
    private Date createTime;

    public Staff(Long id, String name, Double wage, Date createTime) {
        this.id = id;
        this.name = name;
        this.wage = wage;
        this.createTime = createTime;
    }

    public Staff(Date createTime) {
        this.createTime = createTime;
    }

    public Staff() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("wage", wage)
                .append("createTime", createTime)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (id != null ? !id.equals(staff.id) : staff.id != null) return false;
        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        if (wage != null ? !wage.equals(staff.wage) : staff.wage != null) return false;
        return createTime != null ? createTime.equals(staff.createTime) : staff.createTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (wage != null ? wage.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
