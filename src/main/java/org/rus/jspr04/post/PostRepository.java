package org.rus.jspr04.post;

import org.rus.jspr04.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> getAll();

    Optional<Post> getById(final long id);

    Post upsert(final Post post);

    boolean removeById(final long id);
}