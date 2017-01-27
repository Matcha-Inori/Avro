package com.matcha.test.common.schema;

import org.apache.avro.Schema;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matcha on 2017/1/26.
 */
public class SchemaManagerTest
{
    @Before
    public void setUp() throws Exception
    {
        DOMConfigurator.configure("conf/log4jconf.xml");
    }

    @Test
    public void getSchema() throws Exception
    {
        SchemaManager schemaManager = SchemaManager.getInstance();
        Schema userSchema = schemaManager.getSchema("User");
    }

}