package org.yky.test.anno;

/**
 * Created by hp on 2018/3/27.
 */
public class AnoCon {

    @YkyAno(id = 4)
    private int id;

    @YkyAno(id = 2, name = "aaa")
    private String name;

    @YkyAno(id = 3)
    private String defname;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefname() {
        return defname;
    }
}
