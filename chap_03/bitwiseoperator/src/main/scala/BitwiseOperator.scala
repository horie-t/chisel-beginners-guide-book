import chisel3._
import chisel3.stage._

import java.io.PrintWriter

/**
 * DIPスイッチの入力をビット演算する。
 */
class BitwiseOperator extends RawModule {
  val dipSwitch1 = IO(Input(Bool()))    // DIPスイッチからの入力ポート1
  val dipSwitch2 = IO(Input(Bool()))    // DIPスイッチからの入力ポート2
  val led1       = IO(Output(Bool()))   // LEDへの出力ポート1
  val led2       = IO(Output(Bool()))   // LEDへの出力ポート2
  val led3       = IO(Output(Bool()))   // LEDへの出力ポート3
  val led4       = IO(Output(Bool()))   // LEDへの出力ポート4

  // 入力の論理演算結果をLEDへ繋ぐ
  led1 := dipSwitch1 & dipSwitch2  // 論理積(AND)
  led2 := dipSwitch1 | dipSwitch2  // 論理和(OR)
  led3 := dipSwitch1 ^ dipSwitch2  // 排他的論理和(XOR)
  led4 := ~ dipSwitch1                  // 否定(NOT)
}

/**
 * Verilogファイルを生成するための、オブジェクト
 */
object VerilogEmitter extends App {
  val writer = new PrintWriter("target/BitwiseOperator.v")
  writer.write(ChiselStage.emitVerilog(new BitwiseOperator))
  writer.close()
}
