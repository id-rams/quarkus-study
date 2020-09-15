package org.acme;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final Set<Task> listWord = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    Jsonb jsonb;

    public TaskResource() {
        jsonb = JsonbBuilder.create();
        listWord.add(new Task("First task", "nixuya"));
    }

    @GET
    public String list() {

        return jsonb.toJson(listWord);
    }

    @POST
    public String add(@Valid String payload) {
        var fruit = jsonb.fromJson(payload, Task.class);
        listWord.add(fruit);
        return jsonb.toJson(listWord);
    }

    @DELETE
    @Path("/{name}")
    public String delete(@PathParam String name) {
        listWord.removeIf(existingTask -> existingTask.name.contentEquals(name));
        return jsonb.toJson(listWord);
    }
}
