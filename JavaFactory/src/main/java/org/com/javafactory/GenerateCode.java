package org.com.javafactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

public class GenerateCode {
    private static final Logger logger = LoggerFactory.getLogger(GenerateCode.class);
    public static void main(String[] args) {
        logger.info("Generate Code {}", new Date().toString());
    }
}
