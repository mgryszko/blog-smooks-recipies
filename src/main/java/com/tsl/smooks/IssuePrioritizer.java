package com.tsl.smooks;

import com.tsl.smooks.model.Priority;

import java.util.HashMap;
import java.util.Map;

public class IssuePrioritizer {
    private static Map<String, Priority> codePriorities;
    static {
        codePriorities = new HashMap<String, Priority>();
        codePriorities.put("P1", Priority.LOW);
        codePriorities.put("P2", Priority.LOW);
        codePriorities.put("P3", Priority.MEDIUM);
        codePriorities.put("P4", Priority.HIGH);
        codePriorities.put("P5", Priority.HIGH);
    }

    public Priority assignPriorityFromCode(String code) {
        return codePriorities.get(code); 
    }
}
