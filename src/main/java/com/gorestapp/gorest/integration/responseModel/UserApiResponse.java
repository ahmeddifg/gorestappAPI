package com.gorestapp.gorest.integration.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApiResponse {
    private  MetaInfo meta;
    private List<UserAccount> data;
}
