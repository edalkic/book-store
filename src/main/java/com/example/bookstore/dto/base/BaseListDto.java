package com.example.bookstore.dto.base;

import lombok.Builder;

@Builder
public class BaseListDto {
    public Iterable list;
    public int currentPageNumber;
    public int currentPageSize;
    public int totalSize;
}
