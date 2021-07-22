package org.yky.common.exception;


public class BizException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BizException(BizErrorType bizErrorType) {
        super(bizErrorType);
    }

    public BizException(BizErrorType bizErrorType, String message) {
        super(bizErrorType, message);
    }
}
