package dev.unihack

object UniHack {

    private val encounters = HashMap<String, Int>()

    // previous tick
    private val prevSurrounding = HashSet<String>()

    // current tick
    private val currentSurrounding = HashSet<String>()

    /**
     * Meeting others, we will need to keep track of singular events
     * when someone enters surrounding and exits.
     *
     * It might also be useful to put cool downs on encounters to ensure
     * they are not on the edge of the encounter radius.
     */
    fun met(deviceHWID: String) {
        if (prevSurrounding.contains(deviceHWID)) return
        encounters.merge(deviceHWID, 1, Int::plus)
        currentSurrounding.add(deviceHWID)
    }

    /**
     * On every discovery complete interval, we trigger surrounding cleanup
     */
    fun endDiscoveryTick() {
        prevSurrounding.clear()
        prevSurrounding.addAll(currentSurrounding)
        currentSurrounding.clear()
    }
}