package exceptions;

public class ValidationException extends Exception {
    public enum Category {
        ENCODING("Encoding not valid"),
        CONSTRAINTS("Contraints not respected"),
        SOLUTION_FORMAT("The solution file is not correctly formatted"),
        GENERIC("Generic Error");

        private String message;

        Category(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private Category category;

    public ValidationException(Category category, String message) {
        super(message);
        this.category = category;
    }

    public ValidationException(Category category, String message, Throwable throwable) {
        super(message, throwable);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
