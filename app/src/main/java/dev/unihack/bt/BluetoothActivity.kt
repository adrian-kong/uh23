package dev.unihack.bt

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.IntentFilter

class BluetoothActivity : Activity() {
    private var bluetoothAdapter: BluetoothAdapter

    init {
        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter ?: throw NotImplementedError()
        if (!bluetoothAdapter.isEnabled) enableBluetooth()
        bluetoothAdapter.startDiscovery()
        val receiver = BroadcastReceiverImpl()
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)
    }

    private fun enableBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableBtIntent, 1)
    }


}