package com.accentrix.hku.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stringMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbMap {

    private List<Entry> list = new ArrayList<Entry>();

    public void addEntry(String key, String value) {
        Entry entry = new Entry();
        entry.setKey(key);
        entry.setValue(value);
        list.add(entry);
    }

    public List<Entry> getList() {
        return list;
    }

    public void setList(List<Entry> list) {
        this.list = list;
    }

    public static class Entry {

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
