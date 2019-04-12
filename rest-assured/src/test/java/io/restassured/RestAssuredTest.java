/*
 * Copyright 2019 the original author or authors.
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

package io.restassured;

import io.restassured.authentication.FormAuthConfig;
import io.restassured.authentication.OAuthSignature;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RestAssuredTest {

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testBasic() {
    Assert.assertNotNull(RestAssured.basic("user", "pw"));
  }

  @Test
  public void testForm() {
    Assert.assertNotNull(RestAssured.form("user", "pw"));
    Assert.assertNotNull(RestAssured.form("user", "pw", new FormAuthConfig(null, null, null)));

    thrown.expect(IllegalArgumentException.class);
    RestAssured.form("user", null, null);
    // Method is not expected to return due to exception thrown

    thrown.expect(IllegalArgumentException.class);
    RestAssured.form(null, "pw", null);
    // Method is not expected to return due to exception thrown
  }

  @Test
  public void testNtlm() {
    Assert.assertNotNull(RestAssured.ntlm("user", "pw", "ws", "dom"));
  }

  @Test
  public void testOauth() {
    Assert.assertNotNull(RestAssured.oauth("cKey", "cSecret", "aToken", "sToken"));

    final OAuthSignature signature = OAuthSignature.HEADER;
    Assert.assertNotNull(RestAssured.oauth("cKey", "cSecret", "aToken", "sToken", signature));
  }

  @Test
  public void testOauth2() {
    Assert.assertNotNull(RestAssured.oauth2("aToken"));
    Assert.assertNotNull(RestAssured.oauth2("aToken", OAuthSignature.HEADER));
  }

  @Test
  public void testPreemptive() {
    Assert.assertNotNull(RestAssured.preemptive());
  }

  @Test
  public void testWithNoArgs() {
    Assert.assertNotNull(RestAssured.withNoArgs());
  }

}
