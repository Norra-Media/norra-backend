package com.norra.model.response;

import lombok.Data;

@Data
public class PageDto {
    Object lastPage;
    int pageSize;
    int pageNumber;
    int totalPages;
}
