## Clean Monolithic Spring Boot project

### TL;DR

This prototype helps quickly generate skeleton app with following features.
* `Spring boot `
  * `spring mvc`: for web API 
  * `spring data jpa`: data persistence
  * `spring actuator`: health check and to avoid long waiting to server start up during e2e testing
  *  
* `Multiple testing options` 
  * `JMock` testing of application logic in `usecases` module
  * `Spring boot test` for web API testing using Groovy `HTTPClient` and/or `rest-assured` with Java
  * Alternatively, testing API with `mocha`  if you prefer to write tests using JavaScript so that front-end-guy can understand usage easier
* Placeholder project to put your Single Page App sources and build with `maven`


### Quick start

    // 1. to install on local machine
    git clone https://github.com/zukalt/clean-spring-maven-archetype.git
    cd clean-spring-maven-archetype/
    mvn install
    
    // 2. generate project by...
    
    mvn archetype:generate -DarchetypeGroupId=am.nersesyan.archetypes -DarchetypeArtifactId=clean-spring\
     -DartifactId=my-todo-app -DgroupId=com.mycompany
     
    // ... then answer the version number and confirm to generate

And now open and use

### Why do I call it "clean monolithic"?

I guess no one would object it being monolithic. Not sure if you share opinion that every
 great app should start with monolithic architecture and be well decoupled to scale later.

And by clean I mean both:
* it is free of any reference hinting how this code was generated
 (you won't be spending much time to get rid of) 
* it is inspired by book `Clean Architecture` by _R.C. Martin_

