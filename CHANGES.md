# 版本更新 (3.0.0开始算起)：

### 5.1.0 - 更新
 - 修改了部分代码
 - 给一些标签添加了一些api
 - 继续完善序列化让其支持kotlin的数据类和class类

### 5.0.0 - 更新
 - 重写了整个代码
 - 继续编写了序列化和反序列化

### 4.2.0 - 更新
 - 修改了部分代码
 - 把`io.github.xiefrish2021`的用法改成`me.coderfrish`
 - 移除了`org.jetbrains.annotations`依赖

### 4.1.0 - 更新
- 移除了4.0.0不稳定的Java Bean转NBT的支持
- 重写了新的Java Bean转NBT的支持（基本稳定）
- 标签类修从普通的Class类改成了Record类
- 修改了部分代码

### 4.0.0 - 更新
 - 添加了Java Bean转NBT的支持（不稳定）
 - 修改了部分代码

### 3.4.0 - 更新
 - 移除了kotlin委托
 - 正式发布了3.4.0版本

### 3.4.0-20241229-pre - 更新
 - 修改了部分代码
 - 删除了多余的Exception并把他们的用法全改成了NBTException
 - 正式删除了`io.github.xiefrish2021.tag.array.ArrayTag`
 - 普通数组类型的NBT数据类型皆添加了`set(I,Ljava/lang/Object;)Ljava/lang/Object;`方法
 - 修复了无法读取普通数组类型的bug

### 3.4.0-pre - 更新
 - 修改了部分代码
 - 删除了一些无用api，如`Compound`和`List<? extends ITag>`

### 3.3.0 - 更新
 - 修改了部分代码
 - `get(String)Lio/github/xiefrish2021/api/ITag`方法修改成 `get(String)Lio/github/xiefrish2021/compound/NBTElement`
 - 添加了一些kotlin成分：如委托，语法糖

#### 3.2.0 - 更新
 - 删除或合并了一些不必要的接口
 - `NBT.newInstance()`用法更改为 `NBT.getInstance()`

#### 3.1.0 - 更新
 - 修改了SNBTReader里面readArray的会报unchecked的某条代码
 - 把Compound，Array<V extends ITag>等等的接口迁移到了`xyz.frish2021.nbt.api`包
 - 修改了NBT类的基本用法由`new NBT()` 修改成`NBT.newInstance()` 并且添加了单例模式
 - `NBT.newInstance()` 添加了synchronized关键字以防止多次初始化影响线程安全
 - 修复了生成IntArray和ByteArray的SNBT却生成成了List的SNBT。

#### 3.0.0 - 更新
 - 重写了库的代码
 - 允许通过Compound实例生成SNBT
