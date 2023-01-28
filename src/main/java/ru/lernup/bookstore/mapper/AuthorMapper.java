package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Author;
import ru.lernup.bookstore.model.AuthorDto;
import ru.lernup.bookstore.view.AuthorView;


@Component
public class AuthorMapper {
    public AuthorDto mappedToDto(Author author){
        return
                AuthorDto.builder()
                        .id(author.getId())
                        .allNameAuthor(author.getAllNameAuthor())
                        .build();
    }
    public AuthorView mappedToView(AuthorDto authorDto){
        AuthorView authorView = new AuthorView();
        authorView.setId(authorDto.getId());
        authorView.setAllNameAuthor(authorDto.getAllNameAuthor());
        return authorView;
    }
    public AuthorDto mappedFromView(AuthorView authorView){
        return AuthorDto.builder()
                .id(authorView.getId())
                .allNameAuthor(authorView.getAllNameAuthor())
                .build();
    }
    public Author mappedFromDto(AuthorDto authorDto){
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setAllNameAuthor(authorDto.getAllNameAuthor());
        return  author;
    }
}
