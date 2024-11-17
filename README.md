# NBT

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。\
作者：Frish2021

## (1) 用法

### 写操作
#### 源码
```java
import me.coderfrish.nbt.NBTWriter;
import me.coderfrish.nbt.types.TagCompound;

import java.io.File;

public class NBTest {
    public static void main(String[] args) {
        File output = new File("NBT文件输出路径");
        NBTWriter.writeNBTFile(new TagCompound((tag) -> {
            tag.putString("test", "Frish2021");
            tag.putCompound("test1", new TagCompound((tag1) -> {
                tag1.putString("test2", "CoderFrish");
                tag1.putInt("test3", 123456);
            }));
        }), output);
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
import me.coderfrish.nbt.NBTWriter;
import me.coderfrish.nbt.types.TagCompound;

import java.io.File;

public class NBTest {
    public static void main(String[] args) {
        File output = new File("你要读取的NBT文件");
        TagCompound compound = NBTReader.readNBTFile(output);
        System.out.println(compound.getString("test"));
        System.out.println(compound.getCompound("test1").getInt("test3"));
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

## (2) 打算
我打算把SNBT，API简化以及性能优化弄完了就不更新了，反正Minecraft每个版本NBT格式都一样，没什么兼容性问题。

## (3) 最后
该NBT库的其他用法就靠你自己发掘把 :)\
那些比如IntArray，ByteArray，List可以自己看test模块里面的源码。
