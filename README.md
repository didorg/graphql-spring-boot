# GraphQL - Spring Boot  


### Run GraphqlSpringBootApplication(main Class)  

#### Open http://localhost:8080/graphql in `GraphQL Playground`(or one of your preference)  

#### Create a Book   

```
mutation {
  createBook(
    bookName: "Java in Action Panda"
    pages: 2998
    category: COMEDY
    authorName: "Me :)"
    age: 25
  )
}
```
#### Get a Book by id:  

```
query{
  getBook(id:"id returned in the creation above"){
    name
    pages
    author{
      name
    }
    category
  }
}
```
  
  
#### graphql-java dependency:  
- https://www.graphql-java.com/

Feel free to Clone or Fork this project if you need.
