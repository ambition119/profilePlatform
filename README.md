一. 用户画像平台(profile_platform)

  ｜-- profile_server: spring-boot
  
    1.标签管理 (基于mysql实现)
    2.用户画像报告（基于hive+spark + mysqls实现）
    3.用户分群（基于spark+hbase实现）
    4.用户360视角（基于spark+hbase实现）
    
  |-- profile_ui : vue
  
  |-- oneId_server: 用户唯一id,基于spark + Dgraph实现
  
  |-- rest api : 在线接口服务，接口展示


二. 技术架构图

    待补充

三. 效果展示

   ![用户画像平台展示图](./doc/photo/用户画像平台.gif)
