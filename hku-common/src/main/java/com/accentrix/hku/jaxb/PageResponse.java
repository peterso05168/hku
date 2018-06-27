/*
 * $Id: PageResponse.java 12773 2015-02-13 03:57:06Z jierong $
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
package com.accentrix.hku.jaxb;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SuppressWarnings("rawtypes")
public class PageResponse implements Page {

    private final JaxbPage pojo;

    public PageResponse(JaxbPage pojo) {
        this.pojo = pojo;
    }

    @Override
    public List getContent() {
        return pojo.getContent() != null ? pojo.getContent() : Collections.EMPTY_LIST;
    }

    @Override
    public int getNumber() {
        return pojo.getNumber();
    }

    @Override
    public int getNumberOfElements() {
        return pojo.getNumberOfElements();
    }

    @Override
    public int getSize() {
        return pojo.getSize();
    }

    @Override
    public Sort getSort() {
        try {
            return SortAdapter.getInstance().unmarshal(pojo.getSort());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long getTotalElements() {
        return pojo.getTotalElements();
    }

    @Override
    public int getTotalPages() {
        return pojo.getTotalPages();
    }

    public boolean hasContent() {
        return !getContent().isEmpty();
    }

    public boolean hasNextPage() {
        return false;
    }

    public boolean hasPreviousPage() {
        return false;
    }

    public boolean isFirstPage() {
        return false;
    }

    public boolean isLastPage() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public boolean isFirst() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLast() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Page map(Converter converter) {
        // TODO Auto-generated method stub
        return null;
    }

}
