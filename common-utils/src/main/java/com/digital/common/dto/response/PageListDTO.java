package com.digital.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageListDTO<E> {
    private List<E> items;

    private long totalElements;

    private int totalPages;

    private int pageSize;

    private int pageNumber;
}
