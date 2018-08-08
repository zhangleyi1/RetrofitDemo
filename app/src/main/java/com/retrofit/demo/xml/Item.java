package com.retrofit.demo.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Item {
    @Element(name = "title")
    private String title;
}
