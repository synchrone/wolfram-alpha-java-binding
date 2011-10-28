/*
 * Created on Dec 6, 2009
 *
 */
package com.wolfram.alpha.net;



/**
 * Either has httpStatusCode member set to a value other than HttpStatus.SC_OK or wraps another
 * exception such as IOException or HttpException.
 * 
 * @author tgayley
 *
 */
public class WAHttpException extends Exception {

    public int httpStatusCode = 200;
    
    private static final long serialVersionUID = 59955069668288618L;

    
    // Do not add other constructors without reviewing uses of HttpHandlerException,
    // especially in Mathematica PacletManager code. Code depends on the assumption that all these
    // exceptions either have httpStatusCode != SC_OK, or they wrap another exception.
    
    public WAHttpException(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public WAHttpException(Throwable arg0) {
        super(arg0);
    }

    
    public String getMessage() {
        if (httpStatusCode != 200) {
            return statusCodeToMessageString(httpStatusCode);
        } else {
            return super.getMessage();
        }
    }
    
    
    private static String statusCodeToMessageString(int httpStatusCode) {
        switch (httpStatusCode) {
            case 404:
                return "HTTP Error 404: File not found on server";
            case 503:
                return "HTTP Error 503: Service unavailable";
            default:
                return "HTTP Error " + String.valueOf(httpStatusCode);
        }
    }
}
