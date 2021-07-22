package org.yky.common.exception;

public class SystemException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SystemException(SystemErrorType errorType) {
        super(errorType);
    }

    /**
     * 当有未知异常需要处理时，可用这个构造方法，此时将不处理这种异常
     * @param exception
     */
	public SystemException(Exception exception) {
        super(null, exception.getMessage(), exception);
    }

    public SystemException(SystemErrorType errorType, String message) {
        super(errorType, message);
    }
    public SystemException(SystemErrorType errorType, Exception cause) {
        super(errorType, cause.getMessage(), cause);
    }
}
