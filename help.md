main	

​	com.JavaFactory

​			.bean

​				constants.java

​				TableInfo.java

​				FieldInfo.java

​			.builder

​				BuildBase.java

​				BuildTable.java

​				BuildPo.java

​				BuildComment.java

​			.utils

​				JsonUtils.java

​				PropertiesUtils.java

​				StringUtils.java

​				DateUtils.java

​			RunApplication.java

resources

​	template

​		DataTimePatternEnum.txt

​		DataUtils.txtt

​	application.properties

​		

![1747681480793](C:\Users\lanyo\Documents\WeChat Files\wxid_vwx4gcben9f322\FileStorage\Temp\1747681480793.jpg)

![image-20250517192712989](C:\Users\lanyo\AppData\Roaming\Typora\typora-user-images\image-20250517192712989.png)

![image-20250518193049342](C:\Users\lanyo\AppData\Roaming\Typora\typora-user-images\image-20250518193049342.png)



##### 注解@JsonIgnore的使用方法及其效果

1. 作用：在json序列化时将java bean中的一些属性忽略掉，序列化和[反序列化](https://so.csdn.net/so/search?q=反序列化&spm=1001.2101.3001.7020)都受影响。

2. 使用方法：一般标记在属性或者方法上，返回的json数据即不包含该属性。

3. 场景模拟：

   需要把一个List<HistoryOrderBean>转换成json格式的数据传递给前台。但实体类中基本属性字段的值都存储在快照属性字段中。此时我可以在业务层中做处理，把快照属性字段的值赋给实体类中对应的基本属性字段。最后，我希望返回的json数据中不包含这两个快照字段，那么在实体类中快照属性上加注解@JsonIgnore，那么最后返回的json数据，将不会包含goodsInfo和extendsInfo两个属性值。



**@JsonFormat****、****@JSONField****、****@DateTimeFormat****的使用以及其区别**

1、JsonFormat来源于jackson，Jackson是一个简单基于Java应用库，Jackson可以轻松的将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象。Jackson所依赖的jar包较少，简单易用并且性能也要相对高些，并且Jackson社区相对比较活跃，更新速度也比较快。

2、JSONField来源于fastjson，是阿里巴巴的开源框架，主要进行JSON解析和序列化。

3、DateTimeFormat是spring自带的处理框架，主要用于将时间格式化。



**@DateTimeFormat****使用方法****:**

我们可以使用@DateTimeFormat注解**将一个字符串转成一个****Date****对象**，pojo类上的哪个Date类型的字段需要，就需要用**@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")**  标记,这样前端页面传递过来的String类型的时间 '2016-11-11 11:11:11' 会转换为 Date 类型

原因是：页面将数据传到后台，是以字符串的形式。所以时间格式会出错。加上此注解，后台可解析时间格式的字符串。

**@JsonFormat**

但是后台传到前台，前台没办法解析。需要做如下操作。

**@JsonFormat(pattern="yyyy-MM-dd")**

用法 为在属性值上 @JsonFormat(pattern=”yyyy-MM-dd”,**timezone=****”GMT+8****”**)，如果直接使用 @JsonFormat(pattern=”yyyy-MM-dd”)就会出现2018-08-01 08:00:00的情况， 会相差8个小时，因为我们是东八区（北京时间）。所以我们在格式化的时候要指定时区（timezone ）

注意一下事项：

1.如果是小写的hh，那个值代表着1-12，比如，我是在下午16:30传入参数的，而小写的h最大只能接受12的值，传入16就出错了，所有无法将String转为Date类型。

2. 可以**使用在字段上也可以使用在方法**中。如：  public  String

test2(@RequestParam("uid") int id,

**@DateTimeFormat(pattern="yyyy-MM-dd")Date date****,**

UserInfo user) {}