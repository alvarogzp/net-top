package org.alvarogp.nettop.common.presentation.parser.parsed.valid;

public class ValidParsedWithWarning<T> extends ValidParsed<T> {
    private final String warningMessage;

    public ValidParsedWithWarning(T value, String warningMessage) {
        super(value);
        this.warningMessage = warningMessage;
    }

    @Override
    public String getWarningMessage() {
        return warningMessage;
    }
}
