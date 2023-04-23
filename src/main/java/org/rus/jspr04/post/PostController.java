package org.rus.jspr04.post;

import com.google.gson.Gson;
import org.rus.jspr04.post.model.Post;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;

public final class PostController {
    public static final String APPLICATION_JSON = "application/json";

    private final PostService service;
    private final Gson gson = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }

    public void getAll(final HttpServletResponse response) throws IOException {
        wrapper(response, service::getAll);
    }

    public void getById(final HttpServletResponse response, final long id) throws IOException {
        wrapper(response, () -> service.getById(id));
    }

    public void upsert(final HttpServletResponse response, final Reader body) throws IOException {
        wrapper(response, () -> {
            final var post = gson.fromJson(body, Post.class);
            return service.upsert(post);
        });
    }

    public void removeById(final HttpServletResponse response, final long id) throws IOException {
        wrapper(response, () -> service.removeById(id));
    }

    private <T> void wrapper(final HttpServletResponse response, final Supplier<T> supplier) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = supplier.get();
        response.getWriter().print(gson.toJson(data));
    }
}