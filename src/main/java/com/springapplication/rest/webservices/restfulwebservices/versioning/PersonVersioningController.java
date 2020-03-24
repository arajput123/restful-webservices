package com.springapplication.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    // no perfect solution
    // versioning strategy should be finalized upfront

    // uri versioning (twitter)
    // uri pollution
    // api documentation friendly
    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("John Doe");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("John", "Doe"));

    }

    // request params versioning (ex: amazon)
    // caching is possible (version is part of uri)
    // api documentation friendly
    @GetMapping(value="/person/param", params="version=1")
    public PersonV1 paramV1(){
        return new PersonV1("John Doe");

    }

    @GetMapping(value="/person/param", params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("John", "Doe"));

    }

    // Header versioning (microsoft)
    // misuse of http headers
    // caching issue - not easy to cache content based on uri as it would have to always again look at headers
    // not easy to execute requests on the browser (only via plugins postman etc), user needs to  be technically savvy
    @GetMapping(value="/person/header", headers="X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("John Doe");

    }

    @GetMapping(value="/person/header", headers="X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("John", "Doe"));

    }

    // Produces (Gihub)
    // Content Negotiation  or Accept Versioning (using MIME type)
    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("John Doe");

    }

    @GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("John", "Doe"));

    }
}
