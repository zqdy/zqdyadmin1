package com.zqdy.core.common.exception;


/**
 * 执行安全性操作时出现的异�?
 * @author gyf
 *
 */
public class BaseSecurityException extends BaseAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
     * a public constructor for <code>BaseSecurityException</code>.
     * 
     * @param msg
     *            exception message.
     *  
     */
    public BaseSecurityException(String msg) {
        super(msg);
    }

}
