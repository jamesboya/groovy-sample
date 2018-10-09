package com.example.resources;

import com.example.parsec_generated.ResourceException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class SampleExceptionResource {
    @GET
    @Path("npe")
    public String nullException() {
        throw new RuntimeException("exception from SampleExceptionResource",
                new IllegalStateException("illegal state!!!",
                        new NullPointerException()));
    }

    @GET
    @Path("parsecException")
    public String parsecException() {
        throw new ResourceException(500);
    }
}
