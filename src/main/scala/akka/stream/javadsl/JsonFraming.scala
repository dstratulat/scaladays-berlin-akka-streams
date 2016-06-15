/**
 * Copyright (C) 2015-2016 Lightbend Inc. <http://www.lightbend.com>
 */
package akka.stream.javadsl

import akka.NotUsed
import akka.util.ByteString

/** Provides JSON framing stages that can separate valid JSON objects from incoming [[akka.util.ByteString]] objects. */
object JsonFraming {

  /**
   * Returns a Flow that implements a "brace counting" based framing stage for emitting valid JSON chunks.
   *
   * Typical examples of data that one may want to frame using this stage include:
   *
   * **Very large arrays**:
   * {{{
   *   [{"id": 1}, {"id": 2}, [...], {"id": 999}]
   * }}}
   *
   * **Multiple concatenated JSON objects** (with, or without commas between them):
   *
   * {{{
   *   {"id": 1}, {"id": 2}, [...], {"id": 999}
   * }}}
   *
   * The framing works independently of formatting, i.e. it will still emit valid JSON elements even if two
   * elements are separated by multiple newlines or other whitespace characters. And of course is insensitive
   * (and does not impact the emitting frame) to the JSON object's internal formatting.
   *
   * Framing raw JSON values (such as integers or strings) is supported as well.
   *
   * @param maximumObjectLength The maximum length of allowed frames while decoding. If the maximum length is exceeded
   *                            this Flow will fail the stream.
   */
  def bracketCounting(maximumObjectLength: Int): Flow[ByteString, ByteString, NotUsed] =
    akka.stream.scaladsl.JsonFraming.bracketCounting(maximumObjectLength).asJava

}
