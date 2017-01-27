package com.matcha.test.common.exception;

/**
 * Created by Matcha on 2017/1/26.
 */
public class MatchaException extends Exception
{
    public MatchaException()
    {
    }

    public MatchaException(String message)
    {
        super(message);
    }

    public MatchaException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MatchaException(Throwable cause)
    {
        super(cause);
    }

    public MatchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
