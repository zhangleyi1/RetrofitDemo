package com.retrofit.demo.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

//@Root(strict = false) @注解(不严格检查)
@Root(strict = false)
public class Channel {
    @Path("channel")
    @Element(name = "title")
    private String title;
    @Path("channel")
    @ElementList(name = "item", inline = true)
    private List<Item> list;

    @Path("channel")
    @Element(name = "link")
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}