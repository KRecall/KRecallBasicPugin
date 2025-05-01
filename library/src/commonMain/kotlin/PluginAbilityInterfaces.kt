package io.github.octestx.krecall.plugins.basic

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.BackStackEntry

/**
 * 插件能力接口
 */
object PluginAbilityInterfaces {
    /**
     * 该界面会嵌到侧滑栏中的卡片布局
     */
    interface DrawerUI {
        @Composable
        fun DrawerUIShader()
    }

    /**
     * 该界面会嵌到侧滑栏中底部导航栏
     */
    interface MainTabUI {
        val mainTabName: String
        @Composable
        fun MainTabUIShader()
    }

    /**
     * 该界面会嵌到侧滑栏中的设置界面
     */
    interface SettingTabUI {
        val settingTabName: String
        @Composable
        fun SettingTabUIShader()
    }

    /**
     * 注册跳转路径
     */
    interface RoutersBuilder {
        /**
         * key: 每个router的path应该以/开头
         * value: 跳转路径对应的UI
         */
        val routers: Map<String, @Composable (BackStackEntry) -> Unit>
    }
}