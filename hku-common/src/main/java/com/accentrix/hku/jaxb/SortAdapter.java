/*
 * $Id: SortAdapter.java 14589 2015-07-23 06:47:32Z jyang $
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class SortAdapter extends XmlAdapter<JaxbSort, Sort> {

    private static final SortAdapter INSTANCE = new SortAdapter();

    public static SortAdapter getInstance() {
        return INSTANCE;
    }

    @Override
    public JaxbSort marshal(Sort sort) throws Exception {
        if (sort == null) {
            return null;
        }
        JaxbSort pojoSort = new JaxbSort();
        List<JaxbOrder> pojoOrders = new ArrayList<JaxbOrder>();
        pojoSort.setOrders(pojoOrders);
        for (Iterator<Order> orders = sort.iterator(); orders.hasNext();) {
            Order order = orders.next();
            JaxbOrder pojoOrder = new JaxbOrder();
            pojoOrder.setDirection(order.getDirection().name());
            pojoOrder.setProperty(order.getProperty());
            pojoOrders.add(pojoOrder);
        }
        return pojoSort;
    }

    @Override
    public Sort unmarshal(JaxbSort pojo) throws Exception {
        if (pojo == null) {
            return null;
        }
        List<Order> orders = new ArrayList<Sort.Order>();
        for (JaxbOrder pojoOrder : pojo.getOrders()) {
            Direction direction = Direction.fromString(pojoOrder.getDirection());
            Order order = new Order(direction, pojoOrder.getProperty());
            orders.add(order);
        }

        return new Sort(orders);
    }

}
