package com.backend.blog_app.Payloads;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;


}
