package io.github.octestx.krecall.plugins.basic

import io.github.octestx.basic.multiplatform.ui.ui.utils.ToastModel
import io.github.octestx.krecall.plugins.basic.model.RecordData

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
     * Adds timestamp marker with custom tag
     * @param timestamp Target time point
     * @param mark Custom marker identifier
     */
    abstract fun addMark(timestamp: Long, mark: String)

    /**
     * Removes timestamp marker
     * @param timestamp Target time point
     * @param mark Marker identifier to remove
     */
    abstract fun removeMark(timestamp: Long, mark: String)

    /**
     * Lists all timestamps containing specified mark
     * @param mark Marker identifier to filter
     * @return List of matching timestamps
     */
    abstract fun listTimestampWithMark(mark: String): List<Long>

    /**
     * Lists all timestamps excluding specified mark
     * @param mark Marker identifier to exclude
     * @return List of non-matching timestamps
     */
    abstract fun listTimestampWithNotMark(mark: String): List<Long>

    /**
     * Retrieves recorded data by timestamp
     * @param timestamp Target time point
     * @return Complete record data snapshot
     */
    abstract suspend fun getRecordDataByTimestamp(timestamp: Long): RecordData

    /**
     * Gets screen image capture by timestamp
     * @param timestamp Target time point
     * @return Raw image byte array
     */
    abstract suspend fun getRecordScreenImageByTimestamp(timestamp: Long): ByteArray
}