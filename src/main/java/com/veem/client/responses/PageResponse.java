package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse<T>
{
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private boolean previousPage;
    private boolean nextPage;
    private boolean first;
    private boolean last;
    public List<T> content;
    // 'sort' is left out because jackson struggles with converting it (List<Sort.Order>?)
}
