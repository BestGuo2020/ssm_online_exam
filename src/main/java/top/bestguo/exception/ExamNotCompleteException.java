package top.bestguo.exception;

/**
 * 考试试题不完整异常
 */
public class ExamNotCompleteException extends Exception {

    public ExamNotCompleteException(String message) {
        super(message);
    }

    public ExamNotCompleteException() {
        super();
    }
}
