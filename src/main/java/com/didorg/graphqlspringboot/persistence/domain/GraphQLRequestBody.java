package com.didorg.graphqlspringboot.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GraphQLRequestBody {
    private String query;
    private String operationName;
    private Map<String, Object> variables;

}
