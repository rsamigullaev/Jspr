package org.rus.jspr04.post;

import org.rus.jspr04.post.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public final class PostInMemoryDB {
    private final AtomicLong currentId = new AtomicLong(0);

    private final List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public long incrementAndGet() {
        return currentId.incrementAndGet();
    }
}
