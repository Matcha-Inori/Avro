package com.matcha.test.common.schema;

import com.matcha.test.common.exception.MatchaException;
import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Matcha on 2017/1/26.
 */
public class SchemaManager
{
    private static Logger logger = LoggerFactory.getLogger(SchemaManager.class);

    private static SchemaManager instance;

    public static SchemaManager getInstance()
    {
        if(instance == null)
            createInstance();
        return instance;
    }

    private static synchronized void createInstance()
    {
        if(instance == null)
            instance = new SchemaManager();
    }

    private Map<String, Schema> schemaMap;

    private SchemaManager()
    {
        schemaMap = new ConcurrentHashMap<>();
    }

    public Schema getSchema(String schemaName) throws MatchaException
    {
        Schema schema = schemaMap.get(schemaName);
        if(schema == null)
            schema = parseSchema(schemaName);
        return schema;
    }

    private Schema parseSchema(String schemaName) throws MatchaException
    {
        synchronized (schemaName.intern())
        {
            Schema schema = schemaMap.get(schemaName);
            if(schema != null)
                return schema;
            try
            {
                ClassLoader classLoader =
                        (classLoader = Thread.currentThread().getContextClassLoader()) == null ?
                                this.getClass().getClassLoader() : classLoader;
                Schema.Parser parser = new Schema.Parser();
                return parser.parse(classLoader.getResourceAsStream("schema/" + schemaName + ".avsc"));
            }
            catch (IOException e)
            {
                logger.error(this.getClass().getName(), e);
                throw new MatchaException(this.getClass().getName(), e);
            }
        }
    }
}
