package cn.spider.aliyun;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 4220666905005394823L;

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String message) {
        super(message);
    }

    public static ServiceException exception(String message) {
        return new ServiceException(message);
    }

    public static ServiceException exception(Throwable throwable) {
        if (throwable instanceof ServiceException) {
            return new ServiceException(throwable.getMessage());
        }
        return new ServiceException(throwable);
    }

    public static ServiceException of(String msg) {
        throw ServiceException.exception(msg);
    }

    public static ServiceException of(Throwable throwable) {
        throw ServiceException.exception(throwable);
    }
}
