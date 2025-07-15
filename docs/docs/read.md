---
title: NBT 文件读取

prev:
  link: /docs/write
  text: NBT 文件写入
---

# NBT 文件读取

## 基础用法
Java 基础用法
```java
public class NBTReaderTest {
    public static void main(String[] args) {
        try(InputStream stream = new FileInputStream("文件地址")) {
            CompoundTag compound = StreamUtils.fromStream(stream);

            System.out.println(compound.get("name").getAsString());
        }
    }
}
```

Kotlin基础用法
```kotlin
fun main() {
    FileInputStream("文件地址").use {
        val compound = StreamUtils.fromStream(stream)

        println(compound.get("name").asString)
    }
}
```
