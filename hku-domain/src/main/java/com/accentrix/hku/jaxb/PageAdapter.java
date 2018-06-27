/*
 * $Id: PageAdapter.java 14589 2015-07-23 06:47:32Z jyang $
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

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.domain.Page;

@SuppressWarnings("rawtypes")
public class PageAdapter extends XmlAdapter<JaxbPage, Page> {

    public Page unmarshal(final JaxbPage pojo) throws Exception {
        return new PageResponse(pojo);
    }

    public JaxbPage marshal(Page page) throws Exception {
        JaxbPage pojo = new JaxbPage();
        pojo.setContent(page.getContent());
        pojo.setNumber(page.getNumber());
        pojo.setNumberOfElements(page.getNumberOfElements());
        pojo.setSize(page.getSize());
        pojo.setTotalElements(page.getTotalElements());
        pojo.setTotalPages(page.getTotalPages());
        return pojo;
    }

}