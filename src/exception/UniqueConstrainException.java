package exception;

public class UniqueConstrainException extends RuntimeException{
    public UniqueConstrainException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
