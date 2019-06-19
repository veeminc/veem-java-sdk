package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.veem.client.responses.PageResponse;
import com.veem.model.Page;

@NoArgsConstructor(access = PRIVATE)
public class PageConverter
{
    public static <T, U> Page<U> convert(final PageResponse<T> pageResponse, final Function<T, U> entityConverter)
    {
        return Page.<U>builder()
                .content(pageResponse.getContent().stream()
                        .map(entityConverter)
                        .collect(Collectors.toList()))
                .first(pageResponse.isFirst())
                .last(pageResponse.isLast())
                .nextPage(pageResponse.isNextPage())
                .number(pageResponse.getNumber())
                .numberOfElements(pageResponse.getNumberOfElements())
                .previousPage(pageResponse.isPreviousPage())
                .size(pageResponse.getSize())
                .totalElements(pageResponse.getTotalElements())
                .totalPages(pageResponse.getTotalPages())
                .build();
    }
}
