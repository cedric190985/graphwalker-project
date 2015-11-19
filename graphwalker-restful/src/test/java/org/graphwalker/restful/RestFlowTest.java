package org.graphwalker.restful;

/*
 * #%L
 * GraphWalker As A Service
 * %%
 * Copyright (C) 2005 - 2014 GraphWalker
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */


import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.BeforeExecution;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by krikar on 10/10/14.
 */
@GraphWalker(value = "random(edge_coverage(100))", start = "v_EmptyMachine")
public class RestFlowTest extends ExecutionContext implements RestFlow {

    private static final Logger logger = LoggerFactory.getLogger(RestFlowTest.class);

    @BeforeExecution
    public void StartServer() throws Exception {
        ResourceConfig rc = new DefaultResourceConfig();
        rc.getSingletons().add(new Restful());

        String url = "http://0.0.0.0:" + 9191;

        HttpServer server = GrizzlyServerFactory.createHttpServer(url, rc);
    }

    @Test
    public void TestRun() {
        Result result = new TestExecutor(getClass()).execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
            Assert.fail("Test run failed.");
        }
    }

    @Override
    public void e_GetData() {

    }

    @Override
    public void e_SetData() {

    }

    @Override
    public void e_GetStatistics() {

    }

    @Override
    public void e_GetNext() {

    }

    @Override
    public void e_Restart() {

    }

    @Override
    public void v_RestRunning() {

    }

    @Override
    public void e_HasNext() {

    }
}
