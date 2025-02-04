package com.vallengeo.core.config;

import java.io.File;
import java.util.List;

public class Config {
    private Config() {
    }

    public static final String DATE_FORMAT_TIME_TABLE = "dd/MM/yyyy HH:mm";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final Integer TAMANHO_PAGINA = 15;
    public static final Integer COUNT_LAST_RECORDS = 5;
    public static final String VALLENGEO = "vallengeo";
    public static final String TEMP_FOLDER = System.getProperty("java.io.tmpdir");
    public static final String APPLICATION_TEMP_UPLOAD = TEMP_FOLDER + File.separator + VALLENGEO;
}
