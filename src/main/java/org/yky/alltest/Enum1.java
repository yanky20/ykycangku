package org.yky.alltest;

/**
 * Created by lenovo on 2018/12/5.
 */
public enum Enum1 {

    A(2, "哈哈"), B(3, "dd"), C(4, "sad");
    private final Integer key;
    private final String value;

    private Enum1(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Enum1 getEnumByKey(Integer key) {
        if (null == key) {
            return null;
        }
        for (Enum1 temp : Enum1.values()) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
