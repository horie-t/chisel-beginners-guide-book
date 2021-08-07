import chisel3._
import chisel3.stage._

import java.io.PrintWriter

/**
 * DIPスイッチの状態に合わせて、LEDを点灯させるだけのモジュール
 */
class LedOnOff extends MultiIOModule {
  val dipSwitch = IO(Input(Bool()))    // DIPスイッチからの入力ポート
  val led       = IO(Output(Bool()))   // LEDへの出力ポート

  // DIPスイッチからの入力をLEDへ繋ぐ
  led := dipSwitch
}

/**
 * Verilogファイルを生成するための、オブジェクト
 */
object VerilogEmitter extends App {
  val writer = new PrintWriter("target/LedOnOff.v")
  writer.write(ChiselStage.emitVerilog(new LedOnOff))
  writer.close()
}
