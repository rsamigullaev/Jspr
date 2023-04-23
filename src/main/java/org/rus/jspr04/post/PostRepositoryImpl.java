package org.rus.jspr04.post;

import org.rus.jspr04.post.model.Post;
import org.rus.jspr04.util.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class PostRepositoryImpl implements PostRepository {
    private final PostInMemoryDB db;

    public PostRepositoryImpl(final PostInMemoryDB db) {
        this.db = db;
    }

    @Override
    public List<Post> getAll() {
        return db.getPosts();
    }

    @Override
    public Optional<Post> getById(long id) {
        return db.getPosts().stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public Post upsert(Post post) {
        if (post.getId() == 0) return insert(post);
        return update(post);
    }

    private Post insert(Post post) {
        post.setId(db.incrementAndGet());
        db.getPosts().add(post);
        return post;
    }

    private Post update(Post post) {
        final var oldPost = getById(post.getId()).orElseThrow(NotFoundException::new);
        final var oldPostIndex = db.getPosts().indexOf(oldPost);
        db.getPosts().remove(oldPostIndex);
        db.getPosts().add(oldPostIndex, post);
        return post;
    }

    @Override
    public boolean removeById(long id) {
        final var post = getById(id).orElseThrow(NotFoundException::new);
        return db.getPosts().remove(post);
    }
}
