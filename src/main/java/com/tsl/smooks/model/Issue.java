package com.tsl.smooks.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Calendar;
import java.util.Date;

public class Issue {
    private String description;
    private Project project;
    private Priority priority;
    private String involvedPersons;
    private Date created;
    private Date updated;

    public Issue() {
    }

    public Issue(String description, Project project, Priority priority, String involvedPersons, Date created, Date updated) {
        this.description = description;
        this.project = project;
        this.priority = priority;
        this.involvedPersons = involvedPersons;
        this.created = created;
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getInvolvedPersons() {
        return involvedPersons;
    }

    public void setInvolvedPersons(String involvedPersons) {
        this.involvedPersons = involvedPersons;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setCreatedDatePart(Date createdDatetime) {
        createCreatedIfNotInitialized();
        copyDatePart(createdDatetime, created);
    }

    private void createCreatedIfNotInitialized() {
        if (created == null) {
            created = new Date();
        }
    }

    private void copyDatePart(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);

        toCal.set(Calendar.YEAR, fromCal.get(Calendar.YEAR));
        toCal.set(Calendar.MONTH, fromCal.get(Calendar.MONTH));
        toCal.set(Calendar.DAY_OF_MONTH, fromCal.get(Calendar.DAY_OF_MONTH));

        to.setTime(toCal.getTimeInMillis());
    }

    public void setCreatedTimePart(Date createdDatetime) {
        createCreatedIfNotInitialized();
        copyTimePart(createdDatetime, created);
    }

    private void copyTimePart(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);

        toCal.set(Calendar.HOUR_OF_DAY, fromCal.get(Calendar.HOUR_OF_DAY));
        toCal.set(Calendar.MINUTE, fromCal.get(Calendar.MINUTE));
        toCal.set(Calendar.SECOND, fromCal.get(Calendar.SECOND));
        toCal.set(Calendar.MILLISECOND, fromCal.get(Calendar.MILLISECOND));

        to.setTime(toCal.getTimeInMillis());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("description", description)
            .append("project", project)
            .append("priority", priority)
            .append("involvedPersons", involvedPersons)
            .append("created", created)
            .append("updated", updated)
            .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Issue)) {
            return false;
        }
        Issue castOther = (Issue) other;
        return new EqualsBuilder()
            .append(description, castOther.description)
            .append(project, castOther.project)
            .append(priority, castOther.priority)
            .append(involvedPersons, castOther.involvedPersons)
            .append(created, castOther.created)
            .append(updated, castOther.updated)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(description)
            .append(project)
            .append(priority)
            .append(involvedPersons)
            .append(created)
            .append(updated)
            .toHashCode();
    }
}
