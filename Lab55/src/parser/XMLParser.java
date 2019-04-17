package parser;

import annotation.XmlAttribute;
import annotation.XmlObject;
import annotation.XmlTag;
import xml.Attribute;
import xml.Tag;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class XMLParser implements ObjectParser {
    private List<Class> classes = new LinkedList();

    public XMLParser() {
    }

    public Tag parse(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (!obj.getClass().isAnnotationPresent(XmlObject.class)) {
            throw new IllegalArgumentException("class '" + obj.getClass().getSimpleName() + "' is not annotated with 'XmlObject'");
        } else if (this.classes.contains(obj.getClass())) {
            throw new RuntimeException("infinity cicle was founded");
        } else {
            Class aclass = obj.getClass();
            this.classes.add(aclass);
            Tag objTag = new Tag(aclass);
            Map<String, Tag> tags = new HashMap();
            Map<String, List<Attribute>> attributes = new HashMap();
            tags.put(objTag.getName(), objTag);
            Field[] var6 = aclass.getDeclaredFields();
            int var7 = var6.length;

            int var8;
            Tag tag;
            Attribute attribute;
            for(var8 = 0; var8 < var7; ++var8) {
                Field f = var6[var8];
                if (!f.isAnnotationPresent(XmlAttribute.class) && !f.isAnnotationPresent(XmlTag.class)) {
                    System.out.println(f.getName() + " have no annotations");
                } else {
                    if (f.getType() == Void.TYPE) {
                        throw new IllegalArgumentException("field '" + f.getName() + "' returns void");
                    }

                    if (f.isAnnotationPresent(XmlTag.class)) {
                        f.setAccessible(true);
                        tag = new Tag(f, (XmlTag)f.getAnnotation(XmlTag.class));
                        if (f.get(obj) != null) {
                            tag.setValue(f.get(obj).toString());
                        }

                        if (f.getType().isAnnotationPresent(XmlObject.class)) {
                            tag.setChild(this.parse(f.get(obj)));
                        }

                        if (tags.containsKey(tag.getName())) {
                            throw new IllegalArgumentException("tag with name '" + tag.getName() + "' almost exist");
                        }

                        tags.put(tag.getTagName(), tag);
                    }

                    if (f.isAnnotationPresent(XmlAttribute.class)) {
                        f.setAccessible(true);
                        attribute = new Attribute(f);
                        if (f.get(obj) != null) {
                            attribute.setValue(f.get(obj).toString());
                        }

                        if (!((XmlAttribute)f.getAnnotation(XmlAttribute.class)).tag().equals("")) {
                            attribute.setTagName(((XmlAttribute)f.getAnnotation(XmlAttribute.class)).tag());
                        } else {
                            attribute.setTagName(aclass.getSimpleName());
                        }

                        if (((List)attributes.computeIfAbsent(attribute.getTagName(), (k) -> {
                            return new LinkedList();
                        })).contains(attribute)) {
                            throw new IllegalArgumentException("tag with name '" + attribute.getTagName() + "' almost have this attribute");
                        }

                        ((List)attributes.computeIfAbsent(attribute.getTagName(), (k) -> {
                            return new LinkedList();
                        })).add(attribute);
                    }
                }
            }

            Method[] var11 = aclass.getDeclaredMethods();
            var7 = var11.length;

            for(var8 = 0; var8 < var7; ++var8) {
                Method m = var11[var8];
                if (!m.isAnnotationPresent(XmlAttribute.class) && !m.isAnnotationPresent(XmlTag.class)) {
                    System.out.println(m.getName() + " have no annotations");
                } else {
                    if (m.getReturnType() == Void.TYPE) {
                        throw new IllegalArgumentException("field '" + m.getName() + "' returns void");
                    }

                    if (m.getParameterTypes().length > 0) {
                        throw new IllegalArgumentException("method '" + m.getName() + "' have parameters");
                    }

                    if (m.isAnnotationPresent(XmlTag.class)) {
                        tag = new Tag(m, (XmlTag)m.getAnnotation(XmlTag.class));
                        if (m.invoke(obj) != null) {
                            tag.setValue(m.invoke(obj).toString());
                        }

                        if (m.getReturnType().isAnnotationPresent(XmlObject.class)) {
                            tag.setChild(this.parse(m.getReturnType()));
                        }

                        tags.put(tag.getTagName(), tag);
                    }

                    if (m.isAnnotationPresent(XmlAttribute.class)) {
                        attribute = new Attribute(m);
                        if (m.invoke(obj) != null) {
                            attribute.setValue(m.invoke(obj).toString());
                        }

                        if (!((XmlAttribute)m.getAnnotation(XmlAttribute.class)).tag().equals("")) {
                            attribute.setTagName(((XmlAttribute)m.getAnnotation(XmlAttribute.class)).tag());
                        } else {
                            attribute.setTagName(aclass.getSimpleName());
                        }

                        ((List)attributes.computeIfAbsent(attribute.getTagName(), (k) -> {
                            return new LinkedList();
                        })).add(attribute);
                    }
                }
            }

            if (aclass.getSuperclass().isAnnotationPresent(XmlObject.class)) {
                Tag superClassTag = this.parse(aclass.getSuperclass());
                Map<String, Tag> sTags = superClassTag.getChildTags();
                Iterator var17 = sTags.values().iterator();

                while(var17.hasNext()) {
                    Tag t = (Tag)var17.next();
                    tag = (Tag)tags.get(t.getTagName());
                    if (t.getTagName().equals(tag.getTagName())) {
                        if (!t.getName().equals(tag.getName())) {
                            throw new IllegalArgumentException("objects '" + t.getName() + "' and '" + tag.getName() + "' have similar tag");
                        }
                    } else {
                        tags.put(t.getTagName(), t);
                    }
                }
            }

            Iterator var13 = attributes.keySet().iterator();

            while(var13.hasNext()) {
                String s = (String)var13.next();
                Tag curTag = (Tag)tags.get(s);
                Iterator var20 = ((List)attributes.get(s)).iterator();

                while(var20.hasNext()) {
                    attribute = (Attribute)var20.next();
                    curTag.addAttribute(attribute);
                }
            }

            tags.remove(objTag.getName(), objTag);
            objTag.setChildTags(tags);
            return objTag;
        }
    }
}
