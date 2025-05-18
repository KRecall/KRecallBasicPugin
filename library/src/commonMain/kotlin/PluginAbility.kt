package io.github.octestx.krecall.plugins.basic

import io.github.octestx.basic.multiplatform.ui.ui.utils.ToastModel
import io.github.octestx.krecall.plugins.basic.model.SearchIndex
import io.github.octestx.krecall.plugins.basic.model.SearchIndexResult

/**
 * Core plugin capability interface providing common UI and navigation operations.
 */
abstract class PluginAbility {

    /**
     * Displays a customizable toast notification with dismiss callback
     * @param toast Configured toast model containing content and type
     * @param dismissListener Callback triggered when toast disappears
     */
    abstract suspend fun sendToast(toast: ToastModel, dismissListener: () -> Unit = {})

    /**
     * Convenience method for showing simple text toast
     * @param text Content to display
     * @param type Toast classification (default: Normal)
     */
    suspend fun sendToast(text: String, type: ToastModel.Type = ToastModel.Type.Normal) {
        sendToast(ToastModel(text, type = type)) {}
    }

    /**
     * Shows error toast with exception details
     * @param exception The occurred exception to display
     */
    suspend fun sendExceptionToast(exception: Exception) {
        val msg = if (exception.message == null) "${exception.javaClass.name}: Unknown error"
        else "${exception.javaClass.name}: ${exception.message!!}"
        sendToast(ToastModel(msg, type = ToastModel.Type.Error))
    }

    /**
     * Shows warning-level toast notification
     * @param text Warning message content
     */
    suspend fun sendWarningToast(text: String) {
        sendToast(ToastModel(text, type = ToastModel.Type.Warning))
    }

    /**
     * Sends raw text message through plugin channel
     * @param text Message content
     */
    abstract suspend fun sendMessage(text: String)

    /**
     * Navigates to specified route with optional arguments
     * @param route Destination route path
     * @param args Variable arguments as key-value pairs
     */
    abstract suspend fun navigateTo(route: String, vararg args: Pair<String, Any?>)

    /**
     * Returns to previous navigation state
     */
    abstract suspend fun navigateBack()

    /**
     * Registers listener for screen state collection changes
     * @param listener Callback receiving new state (true=active)
     */
    abstract fun addCollectingScreenStateListener(listener: (Boolean) -> Unit)

    /**
     * Retrieves current screen state collection status
     * @return Boolean indicating if collection is active
     */
    abstract suspend fun getCollectingScreenState(): Boolean

    /**
     * Updates screen state collection status
     * @param state New collection state (true=enable)
     */
    abstract suspend fun setCollectingScreenState(state: Boolean)

    /**
     * Registers listener for data processing state changes
     * @param listener Callback receiving new state (true=processing)
     */
    abstract fun addProcessingDataStateListener(listener: (Boolean) -> Unit)

    /**
     * Gets current data processing status
     * @return Boolean indicating if processing is active
     */
    abstract suspend fun getProcessingDataState(): Boolean

    /**
     * Updates data processing status
     * @param state New processing state (true=enable)
     */
    abstract suspend fun setProcessingDataState(state: Boolean)

    /**
     * Searches for records matching specified keywords
     */
    abstract suspend fun search(vararg keyword: String): SearchIndexResult

    /**
     * all (Searches for records matching specified type and timestamp)'s pre available (various fromId) index
     */
    abstract suspend fun preSearchIndex(type: String, timestamp: Long): List<SearchIndex>

    /**
     * all (Searches for records matching specified type and timestamp)'s next available (various fromId) index
     */
    abstract suspend fun nextSearchIndex(type: String, timestamp: Long): List<SearchIndex>

    /**
     * list all (Searches for records matching specified type and timestamp)'s available (various fromId) index
     */
    abstract suspend fun listAvailableSelectionSearchIndex(type: String, timestamp: Long): List<SearchIndex>

    abstract suspend fun <R> getRoverData(searchIndexId: String): R
    abstract suspend fun  getRoverJsonData(searchIndexId: String): String
}