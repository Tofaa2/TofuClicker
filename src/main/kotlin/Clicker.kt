import java.awt.Robot
import java.awt.event.InputEvent
import javax.swing.SwingWorker

class Clicker {

    //var sleepDuration: Long = 1000
    var button: Int = InputEvent.BUTTON1_DOWN_MASK
    var doubleClick: Boolean = false
    var robot: Robot = Robot()
    var worker: SwingWorker<Void, Void>? = null
    var enabled: Boolean = false;
    var randomization: Boolean = false
    var clicks: Long = 0
    var cps: Int = 0
    var minecraftOnly: Boolean = false

    fun start() {
        if (enabled) return
        enabled = true
        worker = object : SwingWorker<Void, Void>() {
            override fun doInBackground(): Void? {
                Thread.sleep(1000)
                while (enabled) {
                    if (cps == 0) continue
                    if (minecraftOnly) {

                    }
                    val sleepDuration = 1000L / cps
                    simulateClick()
                    if (doubleClick) {
                        simulateClick()
                    }
                    if (!randomization) {
                        Thread.sleep(sleepDuration)
                    }
                    else {
                        Thread.sleep(sleepDuration + (Math.random() * 80 + 1).toLong())
                    }
                }
                return null
            }
        }
        worker?.execute()
    }

    fun stop() {
        enabled = false
        worker?.cancel(true)
    }

    private fun simulateClick() {
        robot.mousePress(button)
        robot.mouseRelease(button)
        clicks++
        println("Click")
    }

}