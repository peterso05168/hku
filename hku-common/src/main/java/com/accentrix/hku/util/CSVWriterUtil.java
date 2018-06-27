/*
 * $Id: CSVWriterUtil.java 26979 2018-02-05 08:19:35Z jyang $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.util;

import java.io.StringWriter;

import com.opencsv.CSVWriter;

public class CSVWriterUtil {

    private CSVWriterUtil() {
    }

    public static CSVWriter createCSVWriter(StringWriter stringWriter) {
        return new CSVWriter(stringWriter, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\r\n");
    }
}
