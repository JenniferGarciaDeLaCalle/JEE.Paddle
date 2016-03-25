package business.api.exceptions;

public class AlreadyExistTrainingFieldException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Campo starDate ya existente en la BD";

    public static final int CODE = 1;

    public AlreadyExistTrainingFieldException() {
        this("");
    }

    public AlreadyExistTrainingFieldException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
