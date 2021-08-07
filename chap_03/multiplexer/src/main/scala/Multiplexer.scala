import chisel3._
import chisel3.stage._

import java.io.PrintWriter

class Multiplexer extends RawModule {
  val condition = IO(Input(Bool()))        // 条件
  val consequential = IO(Input(Bool()))    // 条件が真の時の信号
  val alternative = IO(Input(Bool()))      // 条件が偽の時の信号
  val out = IO(Output(Bool()))

  out := Mux(condition, consequential, alternative)
}

/**
 * Verilogファイルを生成するための、オブジェクト
 */
object VerilogEmitter extends App {
  val writer = new PrintWriter("target/Multiplexer.v")
  writer.write(ChiselStage.emitVerilog(new Multiplexer))
  writer.close()
}
