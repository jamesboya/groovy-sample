package com.example;

import com.example.parsec_generated.ParsecExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Default Exception Mapper.
 */
public class DefaultExceptionMapper extends ParsecExceptionMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    /**
     * Default constructor.
     *
     * @param config the config injected by Jersey by default.
     */
    public DefaultExceptionMapper(@Context Configuration config) {
        super(config);
    }

    @Override
    public Response toResponse(RuntimeException e) {
        LOGGER.error("exception: ", e);
        return super.toResponse(e);
    }
}
