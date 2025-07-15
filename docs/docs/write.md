---
title: NBT 文件写入

next:
  link: /docs/read
  text: NBT 文件读取

prev:
  link: /quick-started
  text: 快速开始
---

# NBT 文件写入

## 基础用法
Java基础用法
```java
public class NBTWriterTest {
    public static void main(String[] args) {
        CompoundTag compound = new CompoundTag();
        compound.put("name", new StringTag("CoderFrish"));
        compound.put("age", new IntTag(15))

        try(OutputStream stream = new FileOutputStream("文件地址")) {
            StreamUtils.toStream(compound, stream);
        }
    }
}
```

Kotlin基础用法
```kotlin
fun main() {
    val compound = CompoundTag()
    compound.put("name", StringTag("CoderFrish"))
    compound.put("age", IntTag(15))

    FileOutputStream("文件地址").use {
      StreamUtils.toStream(compound, stream)
    }
}
```
