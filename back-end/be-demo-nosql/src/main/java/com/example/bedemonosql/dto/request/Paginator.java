package com.example.bedemonosql.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paginator {
    private int page;
    private int pageSize;
    private String sortColumn;
    private String condition;
}
