package com.example.domain;

import java.io.IOException;

public interface WebService {

    public String getIndex() throws IOException;

    public String getHealth();

    public String getSleep();

    public String putMessage(String message);

    public String putMessages(String message, int count);

    public String getMessage();

    public String getMessages(int count);

}
