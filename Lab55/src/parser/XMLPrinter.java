package parser;

import xml.Attribute;
import xml.Tag;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class XMLPrinter implements ObjectPrinter {
    public XMLPrinter() {
    }

    public void printObj(Tag tag) {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.print("<" + tag.getName());
        Iterator var2 = tag.getAttributes().iterator();

        while(var2.hasNext()) {
            Attribute a = (Attribute)var2.next();
            if (a.getValue() != null) {
                System.out.print(" " + a.getName() + "=\"" + a.getValue() + "\"");
            }
        }

        System.out.println(">");
        this.print(tag, 1);
        System.out.println("</" + tag.getName() + ">");
    }

    private void print(Tag tag, int level) {
        Iterator var3 = tag.getChildTags().values().iterator();

        while(var3.hasNext()) {
            Tag t = (Tag)var3.next();
            this.printSpace(level);
            System.out.print("<" + t.getName());
            Iterator var5 = t.getAttributes().iterator();

            while(var5.hasNext()) {
                Attribute a = (Attribute)var5.next();
                if (a.getValue() != null) {
                    System.out.print(" " + a.getName() + "=\"" + a.getValue() + "\"");
                }
            }

            System.out.println(">");
            if (t.getChildTag() != null) {
                this.print(t.getChildTag(), level + 1);
            } else if (t.getValue() != null) {
                this.printSpace(level + 2);
                System.out.println(t.getValue());
            }

            this.printSpace(level);
            System.out.println("</" + t.getName() + ">");
        }

    }

    private void printSpace(int level) {
        for(int i = 0; i < level; ++i) {
            System.out.print("   ");
        }

    }

    public void printObj(Tag tag, FileWriter writer) throws IOException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<" + tag.getName());
        Iterator var3 = tag.getAttributes().iterator();

        while(var3.hasNext()) {
            Attribute a = (Attribute)var3.next();
            if (a.getValue() != null) {
                writer.write(" " + a.getName() + "=\"" + a.getValue() + "\"");
            }
        }

        writer.write(">\n");
        this.print(tag, 1, writer);
        writer.write("</" + tag.getName() + ">\n");
        writer.close();
    }

    private void print(Tag tag, int level, FileWriter writer) throws IOException {
        Iterator var4 = tag.getChildTags().values().iterator();

        while(var4.hasNext()) {
            Tag t = (Tag)var4.next();
            this.printSpace(level, writer);
            writer.write("<" + t.getName());
            Iterator var6 = t.getAttributes().iterator();

            while(var6.hasNext()) {
                Attribute a = (Attribute)var6.next();
                if (a.getValue() != null) {
                    writer.write(" " + a.getName() + "=\"" + a.getValue() + "\"");
                }
            }

            writer.write(">\n");
            if (t.getChildTag() != null) {
                this.print(t.getChildTag(), level + 1, writer);
            } else if (t.getValue() != null) {
                this.printSpace(level + 1, writer);
                writer.write(t.getValue());
                writer.write("\n");
            }

            this.printSpace(level, writer);
            writer.write("</" + t.getName() + ">\n");
        }

    }

    private void printSpace(int level, FileWriter writer) throws IOException {
        for(int i = 0; i < level; ++i) {
            writer.write("   ");
        }

    }
}
