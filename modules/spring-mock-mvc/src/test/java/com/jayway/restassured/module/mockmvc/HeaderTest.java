/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// @formatter:off
package com.jayway.restassured.module.mockmvc;

import com.jayway.restassured.module.mockmvc.http.HeaderController;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

public class HeaderTest {

    @BeforeClass
    public static void configureMockMvcInstance() {
        RestAssuredMockMvc.standaloneSetup(new HeaderController());
    }

    @AfterClass
    public static void restRestAssured() {
        RestAssuredMockMvc.reset();
    }

    @Test public void
    can_send_header_using_header_class() {
        given().
                header(new Header("headerName", "John Doe")).
        when().
                get("/header").
        then().
                statusCode(200).
                body("headerName", equalTo("John Doe"));
    }

    @Test public void
    can_send_header_using_header_name_and_value() {
        given().
                header("headerName", "John Doe").
        when().
                get("/header").
        then().
                statusCode(200).
                body("headerName", equalTo("John Doe"));
    }

    @Test public void
    can_send_multiple_headers() {
        given().
                header("headerName", "John Doe").
                header("user-agent", "rest assured").
        when().
                get("/header").
        then().
                statusCode(200).
                body("headerName", equalTo("John Doe")).
                body("user-agent", equalTo("rest assured"));
    }

    @Test public void
    can_send_headers_using_map() {
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("headerName", "John Doe");
        headers.put("user-agent", "rest assured");

        given().
                headers(headers).
        when().
                get("/header").
        then().
                statusCode(200).
                body("headerName", equalTo("John Doe")).
                body("user-agent", equalTo("rest assured"));
    }

    @Test public void
    can_send_headers_using_headers_class() {
        given().
                headers(new Headers(new Header("headerName", "John Doe"), new Header("user-agent", "rest assured"))).
        when().
                get("/header").
        then().
                statusCode(200).
                body("headerName", equalTo("John Doe")).
                body("user-agent", equalTo("rest assured"));
    }
}

// @formatter:on