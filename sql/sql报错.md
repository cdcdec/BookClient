# SQL报错

Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().

原来用的是5.1.5的包，替换成5.1.17之后，如下生成主键的代码都会报错：

```txt

ps = getConnection().prepareStatement(sql.toString()); 
....
rs = ps.getGeneratedKeys();


```

修改成如下内容,不会报错:
```txt

ps = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
...
rs = ps.getGeneratedKeys();


```