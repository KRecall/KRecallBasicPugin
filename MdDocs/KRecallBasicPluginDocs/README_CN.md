# KRecall插件开发文档
## 创建插件基本
1. 父类选择规则：
   - 根据插件类型选择不同的父类
   - 遵循`CaptureScreenByKDESpectaclePlugin`的模式，继承（直接或间接）`PluginBasicExt`

2. 继承限制：
   - 禁止直接继承`PluginBasic`，应使用其功能子类：
     - `AbsCaptureScreenPlugin` 提供截屏能力
     - `AbsStoragePlugin` 提供存储能力
   - `PluginBasicExt` 仅提供基础插件能力

3. 生命周期管理：
   - `PluginBasic`的子类会获得KRecall集成所需的扩展生命周期方法
   - KRecall作为协调平台：
     - 发现所有插件实现
     - 通过以下方法管理插件选中状态：
       - `selected()` 用户激活时调用
       - `unselected()` 取消激活时调用
     - 对选中的插件通过`UI()`方法渲染测试界面

4. 初始化流程：
   - 最终通过`tryInit(context: PluginContext): InitResult`完成初始化
   - 用户在KRecall插件配置页面选择实现方案
   - 同类型能力（如多个`AbsCaptureScreenPlugin`实现）将并列供用户选择

## 实现更多插件能力：
1. `PluginAbilityInterfaces`
   - 用户可选择继承`PluginAbilityInterfaces`中的一些能力接口，例如`MainTabUI`会将你的界面嵌入到主页的侧滑栏中

2. PluginAbility：
   - 每个继承PluginBasicExt的插件都有一个context变量，context中有一个ability变量，可以通过ability调用KRecall各项能力，例如sendToast