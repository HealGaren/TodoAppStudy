package com.example.healgaren.studytodolist;

public class ValidResult extends BaseResult {
    private boolean valid;

    public ValidResult(String message, boolean valid) {
        super(message);
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
