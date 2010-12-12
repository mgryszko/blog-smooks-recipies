package com.tsl.smooks;

import com.tsl.smooks.model.Issue;
import com.tsl.smooks.model.Priority;
import com.tsl.smooks.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.StringSource;

import javax.xml.transform.Source;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SmooksRecipiesTest {

    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Source importedIssues = new StringSource(
        "description,priority,reporter,assignee,createdDate,createdTime,updatedDate,updatedTime\n"
            + "Added phased initialization of javabean cartridge,P1,Ataulfo,Teodorico,2010-10-01,13:10,2010-10-10,20:01\n"
            + "Processing recursive tree like structures with the Javabean Cartridge,P3,Eurico,,2010-10-02,07:15,2010-11-15,09:45"
    );

    private Smooks smooks;
    private ExecutionContext executionContext;

    private List<Issue> expIssues;

    @Before
    public void initSmooks() throws Exception {
        smooks = new Smooks(getResourceFromClassLoader("smooks-config.xml"));
        executionContext = smooks.createExecutionContext();
        executionContext.getBeanContext().addBean("project", new Project("Smooks"));
        executionContext.getBeanContext().addBean("prioritizer", new IssuePrioritizer());
    }

    private InputStream getResourceFromClassLoader(String name) {
        return getClass().getClassLoader().getResourceAsStream(name);
    }

    @Before
    public void createExpIssues() throws Exception {
        Project expProject = new Project("Smooks");
        expIssues = Arrays.asList(
            new Issue("Added phased initialization of javabean cartridge", expProject, Priority.LOW,
                "Ataulfo;Teodorico", DATETIME_FORMAT.parse("2010-10-01 13:10"), DATETIME_FORMAT.parse("2010-10-10 20:01")
            ),
            new Issue(
                "Processing recursive tree like structures with the Javabean Cartridge", expProject, Priority.MEDIUM,
                "Eurico", DATETIME_FORMAT.parse("2010-10-02 07:15"), DATETIME_FORMAT.parse("2010-11-15 09:45")
            )
        );
    }

    @Test
    public void process() throws Exception {
        smooks.filterSource(executionContext, importedIssues);

        List<Issue> issues = (List<Issue>) executionContext.getBeanContext().getBean("issues");
        assertEquals(expIssues, issues);
    }
}