package org.rus.jspr04.util;

import org.rus.jspr04.post.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JConfig {
    @Bean
    public PostInMemoryDB providePostInMemoryDB() {
        return new PostInMemoryDB();
    }

    @Bean
    public PostRepository providePostRepository(final PostInMemoryDB db) {
        return new PostRepositoryImpl(db);
    }

    @Bean
    public PostService providePostService(final PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostController providePostController(final PostService service) {
        return new PostController(service);
    }
}
