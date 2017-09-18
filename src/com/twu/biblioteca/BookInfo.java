package com.twu.biblioteca;

public class BookInfo implements Item {
    String name;
    String author;
    String year;
    boolean beenRented=false;
    BookInfo(String vname, String vauthor, String vyear){
        name=vname;
        author=vauthor;
        year=vyear;
    }

    public void rent(){
        beenRented=true;
    }
    public void returns(){
        beenRented=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o instanceof BookInfo){
            BookInfo bo=(BookInfo)o;
            if(bo.name.equals(this.name)&&bo.author.equals(this.author)&&bo.year.equals(this.year))
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return this.name+"\t"+this.author+"\t"+this.year;
    }
}
