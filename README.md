# NBT

[![MIT License](https://img.shields.io/github/license/XieFrish2021/NBT?style=flat-square)](LICENSE)
![Version](https://img.shields.io/badge/version-preview-version?style=flat-square)
![GitHub all releases](https://img.shields.io/github/downloads/XieFrish2021/NBT/total?style=flat-square)

> 注意：该版本为Preview版本，如有bug请反馈Issues

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。\
版本更新：[CHANGES.md](CHANGES.md)

## (1) 用法
Maven
```xml
<repositories>
    <repository>
        <id>mentha-mc</id>
        <url>https://repo.menthamc.com/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.xiefrish2021</groupId>
        <artifactId>nbt</artifactId>
        <version>${LATEST_VERSION}</version>
    </dependency>
</dependencies>
```

Gradle
```groovy
repositories {
    maven {
        url 'https://repo.menthamc.com/repository/maven-public/'
        name 'mentha-mc'
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation group: 'io.github.xiefrish2021', name: 'nbt', version: '${LATEST_VERSION}'
}
```

Gradle (Kotlin)
```groovy
repositories {
    maven("https://repo.menthamc.com/repository/maven-public/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.github.xiefrish2021:nbt:${LATEST_VERSION}")
}
```

### 写操作
#### 源码
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT.getInstance();
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
java

```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT.getInstance();

        try (InputStream is = new FileInputStream("你要读取的NBT文件")) {
            CompoundTag compound = nbt.readUnnamedNBT(is);
            System.out.println(compound.getString("test"));
            System.out.println(compound.getCompound("test1").getInt("test3"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

kotlin (通过`compound[key]`方法)

```kotlin
fun main() {
    val nbt = NBT.getInstance()
    val compound = nbt.readUnnamedNBT(FileOutputStream("你要读取的NBT文件"))
    
    println(compound["test"].asString())
    println(compound["test1"].asCompound()["test3"].asInt())
}
```

kotlin (通过委托方法)

```kotlin
fun main() {
    val nbt = NBT.getInstance()
    val compound = nbt.readUnnamedNBT(FileOutputStream("你要读取的NBT文件"))
    
    val test: NBTElement by compound
    
    val test1: NBTElement by compound
    val test3: NBTElement by test1.asCompound()
    
    println(test.asString())
    println(test3.asInt())
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
java
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT.getInstance();
        
        CompoundTag tag = nbt.readUnnamedSNBT("{name: Frish2021}");
        System.out.println(tag.get("name"));
    }
}
```

kotlin 同理

#### 效果
控制台输出
```
Frish2021
```

### 生成SNBT
#### 源码
java
```java
public class NBTest {
    public static void main(String[] args) {
        NBT nbt = new NBT.getInstance();
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
kotlin同理

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

## (3) 最后
该NBT库的其他用法就靠你自己发掘把 :)\
那些比如IntArray，ByteArray，List可以自己看test模块或者源码。
