package dev.unihack.bt

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dev.unihack.UniHack

class BroadcastReceiverImpl : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        when (intent.action) {
            BluetoothDevice.ACTION_FOUND -> {
                val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ?: return
                val deviceName = device.name
                val deviceHWID = device.address

                Log.i("RECEIVER", "found %s %s".format(deviceName, deviceHWID))
                UniHack.met(deviceHWID)

            }
        }
        UniHack.endDiscoveryTick()
    }
}