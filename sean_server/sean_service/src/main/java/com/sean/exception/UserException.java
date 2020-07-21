package com.sean.exception;

public class UserException {

    public static class UserNameExisted extends BaseException{
        private static final long serialVersionUID = 3555714415375055302L;
        public UserNameExisted(String msg) {
            super(msg);
        }
    }

    public static class UidExisted extends BaseException
    {
        private static final long serialVersionUID = 8777415230393628334L;
        public UidExisted(String msg) {
            super(msg);
        }
    }

}
