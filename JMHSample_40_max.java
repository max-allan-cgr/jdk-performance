/*
 * Copyright (c) 2015, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openjdk.jmh.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(value = 5)
@State(Scope.Benchmark)
public class JMHSample_40_max {


  // $ java -jar */*/benchmarks.jar ".*InfrastructureBaselineBenchmark.*"
  // Recommended command line options:
  // - JMH options: -prof {Linux: perfnorm, Mac OS X: dtraceasm, Windows: xperfasm}

  /*
   * The performance of below methods should be the same:
   * - method_baseline()
   * - method_blank()
   * - method_inline()
   *
   * The cost of method_dont_inline() is slightly higher.
   */

   public Object object;

   @Benchmark
   public void method_baseline() {
     // this method was intentionally left blank
   }
 
   @Benchmark
   public void method_blank() {
     target_blank();
   }
 
   @Benchmark
   public void method_inline() {
     target_inline();
   }
 
   @Benchmark
   public void method_dont_inline() {
     target_dont_inline();
   }
 
   /*
    * The performance of below methods should be the same:
    * - obj_return()
    * - obj_blackhole_consume()
    *
    * The cost of obj_sink() is slightly higher.
    */
 
   @Benchmark
   public Object obj_return() {
     return object;
   }
 
   @Benchmark
   public void obj_blackhole_consume(Blackhole bh) {
     bh.consume(object);
   }
 
   @Benchmark
   public void obj_sink() {
     sink(object);
   }
 
   private void target_blank() {
     // this method was intentionally left blank
   }
 
   @CompilerControl(CompilerControl.Mode.DONT_INLINE)
   private void target_dont_inline() {
     // this method was intentionally left blank
   }
 
   @CompilerControl(CompilerControl.Mode.INLINE)
   private void target_inline() {
     // this method was intentionally left blank
   }
 
   @CompilerControl(CompilerControl.Mode.DONT_INLINE)
   private void sink(Object object) {
     // this method was intentionally left blank
   }
    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_39
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_40_max.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
