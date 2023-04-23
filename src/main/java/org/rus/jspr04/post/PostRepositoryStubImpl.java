package org.rus.jspr04.post;

import org.rus.jspr04.post.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class PostRepositoryStubImpl implements PostRepository {
    @Override
    public List<Post> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Post> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Post upsert(Post post) {
        return post;
    }

    @Override
    public boolean removeById(long id) {
        return false;
    }
}
