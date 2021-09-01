package com.didorg.graphqlspringboot.controller;

import com.didorg.graphqlspringboot.model.GraphQLRequestBody;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class GraphQLController {
    @Autowired
    private GraphQL graphql;

    @PostMapping(value="graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String,Object>> execute(@RequestBody GraphQLRequestBody body) {
        return Mono.fromCompletionStage(graphql.executeAsync(ExecutionInput.newExecutionInput().query(body.getQuery())
                        .operationName(body.getOperationName()).variables(body.getVariables()).build()))
                .map(ExecutionResult::toSpecification);
    }
}
