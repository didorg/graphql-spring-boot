package com.didorg.graphqlspringboot.controller;

import com.didorg.graphqlspringboot.persistence.domain.GraphQLRequestBody;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class GraphQLController {

    private final GraphQL graphql;

    public GraphQLController(GraphQL graphql) {
        this.graphql = graphql;
    }

    @PostMapping(value="graphql")
    public Mono<Map<String,Object>> execute(@RequestBody GraphQLRequestBody requestBody) {
        return Mono.fromCompletionStage(graphql.executeAsync(ExecutionInput.newExecutionInput().query(requestBody.getQuery())
                        .operationName(requestBody.getOperationName()).variables(requestBody.getVariables()).build()))
                .map(ExecutionResult::toSpecification);
    }
}
