package com.gorestapp.gorest.integration.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationModel {

    private Integer total;
    private Integer pages;
    private Integer page;
    private Integer limit;
    private Links links;
//
//    total: 3788,
//    pages: 190,
//    page: 1,
//    limit: 20,
}
