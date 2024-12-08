# NBT

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。\
作者：Frish2021

## (0) 3.1.0版本 - 更新内容
 - 修改了SNBTReader里面readArray的会报unchecked的某条代码
 - 把Compound，Array<V extends ITag>等等的接口迁移到了`xyz.frish2021.nbt.api`包
 - 修改了NBT类的基本用法由`new NBT()` 修改成`NBT.newInstance()` 并且添加了单例模式
 - `NBT.newInstance()` 添加了synchronized关键字以防止多次初始化影响线程安全

## (1) 用法

### 写操作
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT();
        File output = new File("NBT文件输出路径");
        
        CompoundTag tag = new CompoundTag();
        tag.put("test", new StringTag("Frish2021"));

        CompoundTag tag1 = new CompoundTag();
        tag1.put("test2", new StringTag("CoderFrish"));
        tag1.put("test3", new IntegerTag(123456));
        
        tag.put("test1", tag1);
        nbt.writeUnnamedNBT(tag, output);
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

### 读操作(NBT)
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT();
        File output = new File("你要读取的NBT文件");
        
        CompoundTag compound = nbt.readUnnamedNBT(output);
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

### 读操作(SNBT)
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT();
        
        CompoundTag tag = nbt.readUnnamedSNBT("{name: Frish2021}");
        System.out.println(tag.get("name"));
    }
}
```

#### 效果
控制台输出
```
Frish2021
```

### 新特性 - 生成SNBT
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT();
        CompoundTag tag = new CompoundTag();
        tag.put("test", new StringTag("Frish2021"));

        CompoundTag tag1 = new CompoundTag();
        tag1.put("test2", new StringTag("CoderFrish"));
        tag1.put("test3", new IntegerTag(123456));

        tag.put("test1", tag1);

        System.out.println(nbt.generateSNBT(tag));
    }
}
```

#### 效果
控制台输出
```
{test: Frish2021,test1: {test2: CoderFrish,test3: 123456}}
```

## (2) 其他
接下来的基本就是Bug修复，性能优化，Chunk区块的格式什么的。这个NBT库也没什么可以更新的。\
如果要贡献代码的话可以把仓库Fork下来，写上你的代码，并把写了你的代码的Fork 仓库地址通过邮件发送给我。
邮件：`1573880184@qq.com`
如果有BUG，请发布Issues.

## (3) 历史更新 (3.0.0开始算起)：
#### 3.0.0 - 更新
 - 重写了库的代码
 - 允许通过Compound实例生成SNBT

## (4) 最后
该NBT库的其他用法就靠你自己发掘把 :)\
那些比如IntArray，ByteArray，List可以自己看test模块或者源码。
