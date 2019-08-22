package com.butchy.Demo07注解;
@BookInfo(name = "红楼梦",authors = {"曹雪芹","高鹗"})
public class BookStore {

    @BookInfo(name = "西游记",authors = "吴承恩")
    public void show(){
    }
}
