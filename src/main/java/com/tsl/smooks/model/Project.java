package com.tsl.smooks.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
                              
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
            .append("name", name)
            .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Project)) {
            return false;
        }
        Project castOther = (Project) other;
        return new EqualsBuilder()
            .append(name, castOther.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(name)
            .toHashCode();
    }    
}
