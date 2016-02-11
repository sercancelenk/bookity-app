package sr.api.presentation.vo;

import java.io.Serializable;

/**
 * Created by sercan on 10/02/16.
 */
public class Response implements Serializable{
    private Object data;
    private long duration;
    private boolean valid=true;
    private String error;

    public Response() {
    }

    public Response(Object data) {
        this.data = data;
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}