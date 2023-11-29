package com.example.application;

import java.util.Objects;

public class PutMessageParams {

    private String message;
    private int count = 0;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + count;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PutMessageParams other = (PutMessageParams) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (count != other.count)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "message:" + this.message + ", count: " + this.count;
    }

    public boolean isEmpty() {
        if (Objects.nonNull(this.message) || this.count != 0) {
            return false;
        }
        return true;
    }

}
