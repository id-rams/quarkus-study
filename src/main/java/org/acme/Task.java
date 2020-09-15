package org.acme;

import javax.validation.constraints.NotEmpty;

public class Task {
    @NotEmpty
    public String name;
    @NotEmpty
    public String description;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
