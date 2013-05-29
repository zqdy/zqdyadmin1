package com.zqdy.core.common.exception;




/**
 * 与持久化机制进行交互时抛出的异常
 * @author gyf
 *
 */
public class BaseDBException extends BaseAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /**
     * a public constructor for <code>BaseDBException</code>.
     * 
     * @param msg
     *            exception message.
     *  
     */
    public BaseDBException(String msg) {
        super(msg);
    }
}
