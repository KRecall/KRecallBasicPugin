# KRecall Plugin Development Documentation

Translations: [简体中文](MdDocs/KRecallBasicPluginDocs/README_CN.md)

## Basic Plugin Creation
1. **Parent Class Selection Rules**:
    - Choose different parent classes based on plugin type
    - Follow the pattern of `CaptureScreenByKDESpectaclePlugin` which inherits (directly or indirectly) from `PluginBasicExt`

2. **Inheritance Restrictions**:
    - Direct inheritance from `PluginBasic` is prohibited. Use its functional subclasses:
        - `AbsCaptureScreenPlugin` provides screenshot capabilities
        - `AbsStoragePlugin` provides storage capabilities
    - `PluginBasicExt` only offers basic plugin capabilities

3. **Lifecycle Management**:
    - Subclasses of `PluginBasic` gain extended lifecycle methods required for KRecall integration
    - KRecall acts as a coordination platform that:
        - Discovers all plugin implementations
        - Manages plugin selection state through:
            - `selected()` invoked when user activates an implementation
            - `unselected()` invoked when deactivating
        - Renders testing interfaces for selected plugins via `UI()` method

4. **Initialization Workflow**:
    - Final initialization is completed through `tryInit(context: PluginContext): InitResult`
    - Users select implementations on KRecall plugin configuration page
    - Capabilities of the same type (e.g., multiple `AbsCaptureScreenPlugin` implementations) will be listed for user selection

## Implementing Additional Plugin Capabilities

1. **`PluginAbilityInterfaces`**:
    - Developers can implement capability interfaces from `PluginAbilityInterfaces` to extend features
    - Example: Implementing `MainTabUI` will embed your interface into the main page's sidebar

2. **PluginAbility Access**:
    - All plugins inheriting from `PluginBasicExt` contain a `context` variable
    - The `context` contains an `ability` property for accessing KRecall capabilities:
      ```kotlin
      context.ability.sendToast() // Example usage
      ```
    - Full capability list available via `ability` property