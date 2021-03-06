/*
 * $Id: ColumnPositionMappingStrategyExt.java 13171 2015-03-31 08:01:14Z jierong $
 * 
 * Copyright (c) 2001-2008 Accentrix Company Limited. All Rights Reserved.
 * 
 * Accentrix Company Limited. ("Accentrix") retains copyright on all text, source
 * and binary code contained in this software and documentation. Accentrix grants
 * Licensee a limited license to use this software, provided that this copyright
 * notice and license appear on all copies of the software. The software source
 * code is provided for reference purposes only and may not be copied, modified 
 * or distributed.
 * 
 * THIS SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS," WITHOUT ANY WARRANTY OF
 * ANY KIND UNLESS A SEPARATE WARRANTIES IS PURCHASED FROM ACCENTRIX AND REMAINS
 * VALID.  ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. ACCENTRIX SHALL NOT BE LIABLE
 * FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING OR MODIFYING THE
 * SOFTWARE OR ITS DERIVATIVES.
 * 
 * IN NO EVENT WILL ACCENTRIX BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE
 * USE OF OR INABILITY TO USE SOFTWARE, EVEN IF ACCENTRIX HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 */
package com.accentrix.hku.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.util.CsvUtil;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class ColumnPositionMappingStrategyExt<T> extends ColumnPositionMappingStrategy<T> {
    private static final Logger LOG = LoggerFactory.getLogger(ColumnPositionMappingStrategyExt.class);

    @Override
    protected void createIndexLookup(String[] values) {
        if (indexLookup.isEmpty()) {
            for (int i = 0; i < values.length; i++) {
                String name = CsvUtil.replaceHeader(values[i]);
                LOG.debug("{} -> {}", values[i], name);
                indexLookup.put(name, i);
            }
        }
    }

    @Override
    protected String getColumnName(int col) {
        String name = super.getColumnName(col);
        LOG.debug("getColumnName({}) = {}", col, name);
        return CsvUtil.replaceHeader(name);
    }
}
