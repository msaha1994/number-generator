package com.vmware.task;

import java.io.IOException;

public interface Generator {
    String generate(int goal, int step) throws Exception;
}
