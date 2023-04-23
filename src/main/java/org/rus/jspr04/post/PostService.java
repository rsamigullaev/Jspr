package org.rus.jspr04.post;

import org.rus.jspr04.post.model.Post;
import org.rus.jspr04.util.NotFoundException;

import java.util.List;

public final class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAll() {
        return repository.getAll();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post upsert(Post post) {
        return repository.upsert(post);
    }

    public boolean removeById(long id) {
        repository.removeById(id);
        return true;
    }
}