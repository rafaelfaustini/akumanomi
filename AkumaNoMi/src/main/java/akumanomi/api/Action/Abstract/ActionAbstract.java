package akumanomi.api.Action.Abstract;
import akumanomi.Helper.LoggerHelper;
public class ActionAbstract {
    private boolean hasErrors = false;
    

    public void error(String message) {
        LoggerHelper.error(message);
        this.hasErrors = true;
    }

    public boolean hasErrors() {
        return hasErrors;
    }
}
