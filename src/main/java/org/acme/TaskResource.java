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
        listWord.add(new Task("my first task", "learn docker..."));
    }

    @GET
    public String list() {

        return jsonb.toJson(listWord);
    }

    @POST
    public void add(@Valid String payload) {
        if (!payload.isEmpty() && !payload.contains("<script>")) {
            var fruit = jsonb.fromJson(payload, Task.class);
            listWord.add(fruit);
        }
    }

    @DELETE
    @Path("/{name}")
    public String delete(@PathParam String name) {
        listWord.removeIf(existingTask -> existingTask.name.contentEquals(name));
        return jsonb.toJson(listWord);
    }
}
