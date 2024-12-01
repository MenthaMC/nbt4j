# NBT

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。\
作者：Frish2021

## (1) 用法

### 写操作
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        File output = new File("NBT文件输出路径");
        CompoundTag tag = new CompoundTag();
        tag.put("test", new StringTag("Frish2021"));

        CompoundTag tag1 = new CompoundTag();
        tag1.put("test2", new StringTag("CoderFrish"));
        tag1.put("test3", new IntegerTag(123456));
        
        tag.put("test1", tag1);
        NBTWriter.writeNBT(tag, output);
    }
}
```

#### 效果
建议用IDEA的Minecraft Development插件查看\
或者用其他的NBT浏览程式来查看效果
```nbtt
"": {
	test: Frish2021
	test1: {
		test2: CoderFrish
		test3: 123456
	}
}
```

### 读操作
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        File output = new File("你要读取的NBT文件");
        TagCompound compound = NBTReader.readNBT(output);
        System.out.println(((StringTag) compound.get("test")));
        System.out.println(((IntegerTag) ((CompoundTag) compound.get("test1")).get("test3")));
    }
}
```

被读取的NBT文件类容
```nbtt
"": {
	test: Frish2021
	test1: {
		test2: CoderFrish
		test3: 123456
	}
}
```

#### 效果
控制台输出
```
Frish2021
123456
```

## (2) 其他
接下来的基本就是Bug修复，性能优化什么的。这个NBT库也没什么可以更新的。\
该NBT库的新代码是写Ankair 1.17.1的时候顺便写的，因为Minecraft 1.17.1服务端的Join Game封包要求写NBT格式的Codec \
如果要贡献代码的话可以把仓库Fork下来，写上你的代码，并把写了你的代码的Fork 仓库地址通过邮件发送给我。
邮件：`1573880184@qq.com`


## (3) 最后
该NBT库的其他用法就靠你自己发掘把 :)\
那些比如IntArray，ByteArray，List可以自己看test模块或者源码。
