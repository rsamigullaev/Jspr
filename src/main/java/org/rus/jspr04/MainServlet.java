package org.rus.jspr04;

import org.rus.jspr04.post.*;
import org.rus.jspr04.util.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
        final var db = new PostInMemoryDB();
        final PostRepository repository = new PostRepositoryImpl(db);
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            final var method = Method.fromString(req.getMethod());

            if (method == Method.GET && path.equals("/api/posts")) {
                controller.getAll(resp);
                return;
            }

            final long id = parseOrDefault(path.substring(path.lastIndexOf("/") + 1), 0L);
            if (method == Method.GET && path.matches("/api/posts/\\d+")) {
                controller.getById(resp, id);
                return;
            }

            if (method == Method.POST && path.equals("/api/posts")) {
                controller.upsert(resp, req.getReader());
                return;
            }

            if (method == Method.DELETE && path.matches("/api/posts/\\d+")) {
                controller.removeById(resp, id);
                return;
            }

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (final Throwable cause) {
            cause.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private long parseOrDefault(final String value, final long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (final NumberFormatException ignored) {
            return defaultValue;
        }
    }
}